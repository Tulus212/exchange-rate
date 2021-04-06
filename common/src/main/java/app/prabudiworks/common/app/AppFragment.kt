package app.prabudiworks.common.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class AppFragment<T : ViewDataBinding, out VM : AppViewModel>(private val mResource: Int) : Fragment() {

    protected abstract val viewModel: VM

    protected lateinit var dataBinding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(layoutInflater, mResource, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppViewBehaviour.init(this, dataBinding, viewModel)
        AppViewBehaviour.bindToastObserver(this, viewModel)
    }
}
