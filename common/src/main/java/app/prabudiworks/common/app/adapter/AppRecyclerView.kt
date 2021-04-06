package app.prabudiworks.common.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class AppRecyclerView<BIND: ViewDataBinding, M : Any>(
    diffUtil: DiffUtil.ItemCallback<M>
) : ListAdapter<M, AppRecyclerView.ViewHolder<BIND>>(diffUtil) {

    private lateinit var mInflater: LayoutInflater

    abstract fun onCreateViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding

    abstract fun onPrepareBindViewHolder(binding: ViewDataBinding, model: M)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<BIND> {
        if (!::mInflater.isInitialized) mInflater = LayoutInflater.from(parent.context)
        return ViewHolder(onCreateViewBinding(mInflater, parent, viewType) as BIND)
    }

    override fun onBindViewHolder(holder: ViewHolder<BIND>, position: Int) {
        val item = getItem(position)
        onPrepareBindViewHolder(holder.binding, item)
        holder.binding.executePendingBindings()
    }

    class ViewHolder<BIND: ViewDataBinding> constructor(val binding: BIND) :
        RecyclerView.ViewHolder(binding.root)
}