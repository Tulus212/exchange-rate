package app.prabudiworks.common.binding

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("items")
fun <T> RecyclerView.items(items: List<T>?) {
    if (adapter != null && adapter is ListAdapter<*, RecyclerView.ViewHolder>) {
        (adapter as ListAdapter<T, RecyclerView.ViewHolder>).submitList(items)
    }
}

@BindingAdapter("pagedListItems")
fun <T> RecyclerView.pagedListItems(items: PagedList<T>?) {
    if (adapter != null && adapter is PagedListAdapter<*, RecyclerView.ViewHolder>) {
        (adapter as PagedListAdapter<T, RecyclerView.ViewHolder>).submitList(items) {
            val layoutManager = (layoutManager as LinearLayoutManager)
            val position = layoutManager.findFirstCompletelyVisibleItemPosition()
            if (position != RecyclerView.NO_POSITION) scrollToPosition(position)
        }
    }
}