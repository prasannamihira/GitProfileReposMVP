package com.gapstars.gitprofilemvp.ui.profile

import androidx.annotation.StringRes
import com.gapstars.gitprofilemvp.base.BaseView
import com.gapstars.gitprofilemvp.model.data.response.Repository
import com.gapstars.gitprofilemvp.model.data.response.User

/**
 * Interface providing required method for a view displaying repositories
 */
interface ProfileView : BaseView {

    /**
     * Updates the profile by the specified token
     * @param user the user data that will replace existing ones
     */
    fun updateProfileData(user: User)

    /**
     * Updates the profile pinned repositories by the specified token
     * @param repositories the list of pinned repositories that will replace existing ones
     */
    fun updatePinnedRepositories(repositories: List<Repository>)

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