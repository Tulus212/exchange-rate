package app.prabudiworks.common.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visible")
fun View.visible(visible: Boolean) {
    val newVisibility = if (visible) View.VISIBLE else View.GONE
    if (newVisibility == visibility) return
    visibility = newVisibility
}

@BindingAdapter("shown")
fun View.shown(visible: Boolean) {
    val newVisibility = if (visible) View.VISIBLE else View.INVISIBLE
    if (newVisibility == visibility) return
    visibility = newVisibility
}




