package com.gapstars.gitprofilemvp.ui.profile

import com.gapstars.githubprofilerepository.data.remote.data.request.QueryRequest
import com.gapstars.gitprofilemvp.BuildConfig
import com.gapstars.gitprofilemvp.R
import com.gapstars.gitprofilemvp.app.App
import com.gapstars.gitprofilemvp.base.BasePresenter
import com.gapstars.gitprofilemvp.model.data.response.Repository
import com.gapstars.gitprofilemvp.model.data.response.User
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

    // git User
    lateinit var user: User

    /**
     * When the view is created it will call the profile data from the service and parse to the view
     */
    override fun onViewCreated() {

        loadGitProfile()
    }

    /**
     * Loads the git profile data from the API and presents them in the view when retrieved,
     * or shows error if any.
     */
    fun loadGitProfile() {
        view.showLoading()

        var repoPinnedList = ArrayList<Repository?>()
        var repoList = ArrayList<Repository?>()
        var repoStarredList = ArrayList<Repository?>()

        var userName = "GabrielBB" // test user for fetch github profile data, This parameter should parse from the front end

        // query string parameter to fetch data from the graphql api
        // this string contains all the data required from api to fetch
        var queryString = App.instance.resources.getString(R.string.query_profile_data)

        queryString = queryString.replace("param01", "\"" + userName + "\"")

        // query string request object
        val queryRequest = QueryRequest(queryString)

        // subscribe profile data from graphql api service
        subscription = gitProfileApi
            .fetchProfileData(queryRequest, BuildConfig.ACCESS_TOKEN)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                {
                    if (it != null) {
                        user = it.body()?.data?.user!!
                        var followersCount = user.followers?.totalCount
                        App.user = user

                        // pinned repository items
                        for (item in it.body()?.data?.user?.pinnedItems?.nodes!!)
                            repoPinnedList.add(item)
                        // repository items
                        for (item in it.body()?.data?.user?.repositories?.edges!!)
                            repoList.add(item.repository)
                        // starred repository items
                        for (item in it.body()?.data?.user?.starredRepositories?.edges!!)
                            repoStarredList.add(item.repository)

                        // update the pinned repository list view
                        view.updatePinnedRepositories(repoPinnedList as List<Repository>)

                        // update the user profile data
                        view.updateProfileData(user)
                    }
                },
                { view.showError(R.string.unknown_error) }
            )
    }

    /**
     * When view going to destroy, it will dispose the existing subscription
     */
    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}