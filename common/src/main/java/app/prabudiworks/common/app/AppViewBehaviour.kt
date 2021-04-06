package app.prabudiworks.common.app

import android.app.Activity
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import app.prabudiworks.common.Common

object AppViewBehaviour {

    fun <VDB : ViewDataBinding, VM : AppViewModel> init(
        owner: LifecycleOwner,
        dataBinding: VDB,
        viewModel: VM
    ) {
        dataBinding.lifecycleOwner = owner // to initialize the viewBinding lazy delegate

        // bindings
        if (owner is HasViewModel) dataBinding.setVariable(owner.brViewModelId, viewModel)
        else if (owner is Fragment && owner.requireActivity() is HasViewModel) {
            val viewModelIdentifier = (owner.requireActivity() as HasViewModel).brViewModelId
            dataBinding.setVariable(viewModelIdentifier, viewModel)
        } else Common.getViewModelBindingId()?.let { id -> dataBinding.setVariable(id, viewModel) }

        if (owner is HasBindings) owner.setupBindings()
        if (owner is HasViewModel || owner is HasBindings) dataBinding.executePendingBindings()

        // setup / preparation related
        if (owner is HasViews) owner.setupViews()
        if (owner is HasObservers) owner.setupObservers()
    }

    fun bindToastObserver(owner: LifecycleOwner, viewModel: AppViewModel) {
        viewModel.showToastEvent.observe(owner) { message ->
            when (owner) {
                is Activity -> Toast.makeText(owner, message, Toast.LENGTH_SHORT).show()
                is Fragment -> Toast.makeText(owner.requireActivity(), message, Toast.LENGTH_SHORT)
                    .show()
                is DialogFragment -> Toast.makeText(
                    owner.requireActivity(),
                    message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}