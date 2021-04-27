package com.gapstars.gitprofilemvp.network

import com.gapstars.githubprofilerepository.data.remote.data.request.QueryRequest
import com.gapstars.gitprofilemvp.model.data.response.GitProfileDataResponse
import com.gapstars.gitprofilemvp.model.RepositoryModel
import com.gapstars.gitprofilemvp.utils.Config
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

/**
 * The interface which provides methods to get result of webservices
 */
interface GitProfileApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<RepositoryModel>>

    /**
     * query github profile data
     *
     * @param accessToken
     * @param request
     */
    @Headers(Config.CONTENT_TYPE_JSON)
    @POST("graphql")
    fun fetchProfileData(
        @Body request: QueryRequest,
        @Header(Config.AUTHORIZATION) accessToken: String
    ): Flowable<Response<GitProfileDataResponse>>
}