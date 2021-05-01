package com.gapstars.gitprofilemvp.network

import com.gapstars.githubprofilerepository.data.remote.data.request.QueryRequest
import com.gapstars.gitprofilemvp.model.data.response.GitProfileDataResponse
import com.gapstars.gitprofilemvp.utils.Config
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * The interface which provides methods to get result of webservices
 */
interface GitProfileApi {

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