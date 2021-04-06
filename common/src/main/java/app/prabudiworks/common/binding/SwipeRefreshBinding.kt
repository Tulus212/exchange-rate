package app.prabudiworks.common.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.onRefresh(callback: Runnable) {
    setOnRefreshListener {
        callback.run()
    }
}

@BindingAdapter("onShortRefresh")
fun SwipeRefreshLayout.shortRefresh(callback: Runnable) {
    setOnRefreshListener {
        isRefreshing = false
        callback.run()
    }
}

@BindingAdapter(value = ["loading", "loadingAttrChanged"], requireAll = false)
fun SwipeRefreshLayout.loadingState(
    isLoading: Boolean,
    loadingAttrChanged: InverseBindingListener
) {
    if (isRefreshing != isLoading) {
        isRefreshing = isLoading
        loadingAttrChanged.onChange()
    }
}

@InverseBindingAdapter(attribute = "loading", event = "loadingAttrChanged")
fun SwipeRefreshLayout.onLoadingStateChanged(): Boolean = isRefreshing