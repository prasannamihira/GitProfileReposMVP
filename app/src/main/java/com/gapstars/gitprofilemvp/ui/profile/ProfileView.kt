package com.gapstars.gitprofilemvp.ui.profile

import androidx.annotation.StringRes
import com.gapstars.gitprofilemvp.base.BaseView
import com.gapstars.gitprofilemvp.model.data.response.Repository

/**
 * Interface providing required method for a view displaying repositories
 */
interface ProfileView : BaseView {

    /**
     * Updates the profile by the specified token
     * @param profile the list of repositories that will replace existing ones
     */
    fun updateProfile(profile: List<Repository>)

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun showError(error: String)

    /**
     * Displays an error in the view
     * @param errorResId the resource id of the error to display in the view
     */
    fun showError(@StringRes errorResId: Int) {
        this.showError(getContext().getString(errorResId))
    }

    /**
     * Displays the loading indicator of the view
     */
    fun showLoading()

    /**
     * Hides the loading indicator of the view
     */
    fun hideLoading()
}