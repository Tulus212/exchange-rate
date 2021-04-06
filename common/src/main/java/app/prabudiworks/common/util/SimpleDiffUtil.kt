package app.prabudiworks.common.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

abstract class SimpleDiffUtil<T : Any> : DiffUtil.ItemCallback<T>() {

    abstract fun getIdentifier(item: T): Any

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return getIdentifier(oldItem) == getIdentifier(newItem)
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

}