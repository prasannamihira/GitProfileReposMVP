package com.gapstars.gitprofilemvp.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gapstars.gitprofilemvp.model.data.response.Viewer
import com.gapstars.gitprofilemvp.R
import com.gapstars.gitprofilemvp.base.BaseActivity
import com.gapstars.gitprofilemvp.databinding.ActivityProfileBinding
import com.gapstars.gitprofilemvp.model.data.response.Repository

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
    private val reposAdapter = RepositoryAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        binding.adapter = reposAdapter

        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    /**
     * Updates the profile by the specified token
     * @param profile the list of repositories that will replace existing ones
     */
    override fun updateProfile(profile: List<Repository>) {
        reposAdapter.updatePosts(profile)

    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun instantiatePresenter(): ProfilePresenter {
        return ProfilePresenter(this)
    }
}