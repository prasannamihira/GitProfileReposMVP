package com.gapstars.gitprofilemvp.profile

import com.gapstars.githubprofilerepository.data.remote.data.request.QueryRequest
import com.gapstars.gitprofilemvp.BuildConfig
import com.gapstars.gitprofilemvp.R
import com.gapstars.gitprofilemvp.app.App
import com.gapstars.gitprofilemvp.model.data.response.GitProfileDataResponse
import com.gapstars.gitprofilemvp.model.data.response.Repository
import com.gapstars.gitprofilemvp.network.GitProfileApi
import com.gapstars.gitprofilemvp.ui.profile.ProfilePresenter
import com.gapstars.gitprofilemvp.ui.profile.ProfileView
import io.reactivex.Flowable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ProfilePresenterTest {

    @Rule
    val mOverrideSchedulersRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var apiService: GitProfileApi

    @Mock
    private lateinit var profileView: ProfileView

    @Mock
    private lateinit var gitRepoResponse: GitProfileDataResponse

    private lateinit var profilePresenter: ProfilePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        profilePresenter = ProfilePresenter(profileView)
    }

    @After
    fun teardown() {
        profilePresenter.onViewDestroyed()
    }

    @Test
    fun loadGitProfileDataFromApiAndLoadInToView() {
        val responseObservable: Flowable<Response<GitProfileDataResponse>> =
            Flowable.just(gitRepoResponse)

        // query string parameter
        var queryString = App.instance.resources.getString(R.string.query_profile_data)

        // query string request object
        val queryRequest = QueryRequest(queryString)

        Mockito.`when`(apiService.fetchProfileData(queryRequest, BuildConfig.ACCESS_TOKEN))
            .thenReturn(
                responseObservable
            )

        profilePresenter.loadGitProfile()

        val inOrder = Mockito.inOrder(profileView)

        // verify the progress loading visible
        inOrder.verify(profileView).showLoading()
        // verify the progress loading gone
        inOrder.verify(profileView).hideLoading()

        // list of git repositories
        var repoList = ArrayList<Repository?>()
        for (item in gitRepoResponse.data.user.repositories?.edges!!) {
            repoList.add(item.repository)
        }

        // verify the data is correct
        inOrder.verify(profileView).updateProfile(gitRepoResponse.data.user)
    }
}