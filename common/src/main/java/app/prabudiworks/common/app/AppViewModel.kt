package app.prabudiworks.common.app

import androidx.lifecycle.ViewModel
import app.prabudiworks.common.lifecycle.MutableLiveEvent

abstract class AppViewModel : ViewModel() {

    val showToastEvent by lazy { MutableLiveEvent<String>() }

    protected open fun showAlert(message: String) {
        showToastEvent.set(message)
    }

    protected open fun showAlert(exception: Exception) {
        showToastEvent.set(exception.message.orEmpty())
    }

}
