package com.gapstars.gitprofilemvp.app

import android.app.Application
import com.gapstars.gitprofilemvp.model.data.response.User

class App: Application() {

    companion object {
        // application instance
        lateinit var instance: App
        lateinit var user: User
    }

    override fun onCreate() {
        super.onCreate()

        // Initialize Application global instance
        instance = this
    }
}