package app.prabudiworks.common.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class AppDialogFragment<out T : ViewDataBinding, out VM : AppViewModel>(resource: Int) : DialogFragment() {

    protected abstract val viewModel: VM

    protected val dataBinding: T by lazy {
        DataBindingUtil.inflate(layoutInflater, resource, null, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppViewBehaviour.init(this, dataBinding, viewModel)
        AppViewBehaviour.bindToastObserver(this, viewModel)
    }

}