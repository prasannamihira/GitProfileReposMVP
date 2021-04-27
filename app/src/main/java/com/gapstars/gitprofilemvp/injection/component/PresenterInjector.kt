package com.gapstars.gitprofilemvp.injection.component

import dagger.BindsInstance
import dagger.Component
import com.gapstars.gitprofilemvp.base.BaseView
import com.gapstars.gitprofilemvp.injection.module.ContextModule
import com.gapstars.gitprofilemvp.injection.module.NetworkModule
import com.gapstars.gitprofilemvp.ui.profile.ProfilePresenter
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param profilePresenter PostPresenter in which to inject the dependencies
     */
    fun inject(profilePresenter: ProfilePresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder

        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}