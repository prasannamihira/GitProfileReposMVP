package com.gapstars.gitprofilemvp.injection.module

import com.gapstars.gitprofilemvp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import com.gapstars.gitprofilemvp.network.GitProfileApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Git profile service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Git profile service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGitProfileApi(retrofit: Retrofit): GitProfileApi {
        return retrofit.create(GitProfileApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}