package app.prabudiworks.common.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

class AppRecyclerViewImpl<M : Any>(
    diffUtil: DiffUtil.ItemCallback<M>,
    private val mOnEachItemType: (data: M) -> Int,
    private val mOnCreateViewBinding: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) -> ViewDataBinding?,
    private val mOnBinding: (binding: ViewDataBinding, model: M) -> Unit
) : AppRecyclerView<ViewDataBinding, M>(diffUtil) {

    constructor(
        diffUtil: DiffUtil.ItemCallback<M>,
        onCreateViewBinding: (
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) -> ViewDataBinding? = { _, _, _ -> null},
        onBinding: (binding: ViewDataBinding, model: M) -> Unit = { _, _ -> }
    ) : this(diffUtil, { 0 }, onCreateViewBinding, onBinding)

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding = mOnCreateViewBinding(inflater, parent, viewType)!!

    override fun onPrepareBindViewHolder(binding: ViewDataBinding, model: M) {
        mOnBinding(binding, model)
    }

    override fun getItemViewType(position: Int): Int {
        return mOnEachItemType(getItem(position))
    }

}