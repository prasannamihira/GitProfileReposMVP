package com.gapstars.gitprofilemvp.utils

interface Config {

    companion object {
        val TIME_OUT = 20

        const val CONTENT_TYPE_JSON = "Content-Type:application/json"
        const val ACCESS_TOKEN_PREFIX = "Bearer"
        const val AUTHORIZATION = "Authorization"

        const val RESPONSE_SUCCESS = 200
        const val AUTHENTICATION_ERROR = "401"
        const val NOT_FOUND = "404"
    }
}