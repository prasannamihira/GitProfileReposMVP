package com.gapstars.gitprofilemvp.ui.profile

import com.gapstars.githubprofilerepository.data.remote.data.request.QueryRequest
import com.gapstars.gitprofilemvp.BuildConfig
import com.gapstars.gitprofilemvp.R
import com.gapstars.gitprofilemvp.app.App
import com.gapstars.gitprofilemvp.base.BasePresenter
import com.gapstars.gitprofilemvp.model.data.response.Repository
import com.gapstars.gitprofilemvp.network.GitProfileApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * The Presenter that will present the Profile view.
 * @param profileView the Profile view to be presented by the presenter
 * @property gitProfileApi the API interface implementation
 * @property subscription the subscription to the API call
 */
class ProfilePresenter(profileView: ProfileView) : BasePresenter<ProfileView>(profileView) {
    @Inject
    lateinit var gitProfileApi: GitProfileApi

    private var subscription: Disposable? = null

    override fun onViewCreated() {

        loadGitProfile()
    }

    /**
     * Loads the git profile data from the API and presents them in the view when retrieved, or shows error if
     * any.
     */
    private fun loadGitProfile() {
        view.showLoading()

        var repoList = ArrayList<Repository?>()

        // query string parameter
        var queryString = App.instance.resources.getString(R.string.query_profile_data)

        // query string request object
        val queryRequest = QueryRequest(queryString)

        subscription = gitProfileApi
            .fetchProfileData(queryRequest, BuildConfig.ACCESS_TOKEN)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                {
                    if (it != null) {
                        for (item in it.body()?.data?.viewer?.repositories?.edges!!)
                            repoList.add(item.repository)

                        view.updateProfile(repoList as List<Repository>)
                    }
                },
                { view.showError(R.string.unknown_error) }
            )
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}