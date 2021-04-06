package app.prabudiworks.common.app

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class AppActivity<out T : ViewDataBinding, out VM : AppViewModel>(@LayoutRes val layoutResID: Int) : AppCompatActivity() {

    protected abstract val viewModel: VM

    protected val dataBinding by lazy { DataBindingUtil.setContentView<T>(this, layoutResID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppViewBehaviour.init(this, dataBinding, viewModel)
        AppViewBehaviour.bindToastObserver(this, viewModel)
    }

}
