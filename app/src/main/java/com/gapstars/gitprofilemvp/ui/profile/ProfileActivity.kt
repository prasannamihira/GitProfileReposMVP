package com.gapstars.gitprofilemvp.ui.profile

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gapstars.gitprofilemvp.R
import com.gapstars.gitprofilemvp.base.BaseActivity
import com.gapstars.gitprofilemvp.databinding.ActivityProfileBinding
import com.gapstars.gitprofilemvp.model.data.response.Repository
import com.gapstars.gitprofilemvp.model.data.response.User

/**
 * Activity displaying the list of repositories
 */
class ProfileActivity : BaseActivity<ProfilePresenter>(), ProfileView {
    /**
     * DataBinding instance
     */
    private lateinit var binding: ActivityProfileBinding

    /**
     * The adapter for the list of repositories
     */
    private val reposPinnedAdapter = RepositoryPinnedAdapter(this)
    private val reposAdapter = RepositoryAdapter(this)
    private val reposStarredAdapter = RepositoryStarredAdapter(this)

    private lateinit var mRunnable: Runnable
    private lateinit var mHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        binding.adapterPinned = reposPinnedAdapter
        binding.adapter = reposAdapter
        binding.adapterStarred = reposStarredAdapter

        presenter.onViewCreated()

        // Initialize the handler instance
        mHandler = Handler()

        binding.srlProfile.setOnRefreshListener {
            // Initialize a new Runnable
            mRunnable = Runnable {

                // Hide swipe to refresh icon animation
                binding.srlProfile.isRefreshing = false

                presenter.onViewCreated()
            }

            // Execute the task after specified time
            mHandler.postDelayed(
                mRunnable,
                500
            )
        }
    }

    /**
     * Destroy the view when activity finished
     */
    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    /**
     * Update the profile view with api data
     *
     * @param user
     */
    override fun updateProfileData(user: User) {
        binding.user = user

        // profile image loading
        binding.ivProfile.post {
            Glide.with(this)
                .load(user.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivProfile)
        }
    }

    /**
     * Updates the pinned repository list by the specified token
     * @param repositories the list of pinned repositories that will replace existing ones
     */
    override fun updatePinnedRepositories(repositories: List<Repository>) {
        reposPinnedAdapter.updatePinnedRepositories(repositories)
    }

    /**
     * Updates the repository list by the specified token
     * @param repositories the list of repositories that will replace existing ones
     */
    override fun updateRepositories(repositories: List<Repository>) {
        reposAdapter.updateRepositories(repositories)
    }

    /**
     * Updates the starred repository list by the specified token
     * @param repositories the list of starred repositories that will replace existing ones
     */
    override fun updateStarredRepositories(repositories: List<Repository>) {
        reposStarredAdapter.updateStarredRepositories(repositories)
    }

    /**
     * Show toast error
     *
     * @param error
     */
    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    /**
     * Show progress view
     */
    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    /**
     * Hide progress view
     */
    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    /**
     * instantiate the presenter for Profile view
     */
    override fun instantiatePresenter(): ProfilePresenter {
        return ProfilePresenter(this)
    }
}