// Safe here as method are used by Data binding
@file:Suppress("unused")

package com.gapstars.gitprofilemvp.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gapstars.gitprofilemvp.ui.profile.RepositoryPinnedAdapter

/**
 * Sets an adapter to a RecyclerView (to be used in view with one RecyclerView)
 * @param view the RecyclerView on which to set the adapter
 * @param adapterPinned the adapter to set to the RecyclerView
 */
@BindingAdapter("adapterPinned")
fun setAdapter(view: RecyclerView, adapterPinned: RepositoryPinnedAdapter) {
    view.adapter = adapterPinned
}

/**
 * Sets a LayoutManager to a RecyclerView (to be used in view with one RecyclerView)
 * @param view the RecyclerView on which to set the LayoutManager
 * @param layoutManager the LayoutManager to set to the RecyclerView
 */
@BindingAdapter("layoutManager")
fun setLayoutManager(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    view.layoutManager = layoutManager
}