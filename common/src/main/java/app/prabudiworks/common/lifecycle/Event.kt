package app.prabudiworks.common.lifecycle

/**
 * A single events concept for MVVM.
 */
class Event<out T>(private val content: T) {

    var hasBeenUsed = false
        private set

    fun getContentIfNotUsed(): T? {
        return if (hasBeenUsed) {
            null
        } else {
            hasBeenUsed = true
            content
        }
    }

    fun peekContent(): T = content

}