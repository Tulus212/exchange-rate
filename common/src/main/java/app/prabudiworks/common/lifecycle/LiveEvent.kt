package app.prabudiworks.common.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

open class LiveEvent<T>(protected val default: T? = null) {

    protected val action: MutableLiveData<Event<T>> = MutableLiveData()

    fun observe(lifecycleOwner: LifecycleOwner, callback: (data: T) -> Unit) {
        action.observe(lifecycleOwner, Observer {
            action.value?.let {
                if (!it.hasBeenUsed) {
                    callback(it.getContentIfNotUsed() ?: it.peekContent())
                }
            }
        })
    }

}