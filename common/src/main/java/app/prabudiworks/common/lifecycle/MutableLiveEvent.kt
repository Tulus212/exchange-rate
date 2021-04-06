package app.prabudiworks.common.lifecycle

class MutableLiveEvent<T>(default: T? = null) : LiveEvent<T>(default) {

    fun set(value: T? = null) {
        action.postValue(Event(value ?: default ?: return))
    }

}