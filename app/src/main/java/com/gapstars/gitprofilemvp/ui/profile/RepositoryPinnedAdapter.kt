package com.gapstars.gitprofilemvp.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gapstars.gitprofilemvp.R
import com.gapstars.gitprofilemvp.databinding.ListItemRepoBinding
import com.gapstars.gitprofilemvp.model.data.response.Repository
import com.gapstars.gitprofilemvp.model.data.response.User

/**
 * Adapter for the list of the repositories
 * @property context Context in which the application is running
 */
class RepositoryPinnedAdapter(private val context: Context) :
    RecyclerView.Adapter<RepositoryPinnedAdapter.RepositoryViewHolder>() {
    /**
     * The list of repositories of the adapter
     */
    private var repositories: List<Repository> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ListItemRepoBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_repo, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    /**
     * Updates the list of repositories of the adapter
     * @param repositories the new list of repositories of the adapter
     */
    fun updatePinnedRepositories(repositories: List<Repository>) {
        this.repositories = repositories

        // notify adapter to update the latest data
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Repository item
     */
    class RepositoryViewHolder(private val binding: ListItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a repository data into the view
         */
        fun bind(repository: Repository) {
            binding.repo = repository
            binding.executePendingBindings()
        }
    }
}