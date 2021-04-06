package app.prabudiworks.common.extension

import androidx.lifecycle.MutableLiveData

/**
 * Replace first data found within a MutableList.
 */
fun <T> MutableList<T>.replaceFirst(newItem: T, predicate: (item: T) -> Boolean): MutableList<T> {
    val index = indexOfFirst { predicate(it) }
    removeAt(index)
    add(index, newItem)
    return this
}


/**
 * Apply the same function as MutableList.replaceFirst() but for a MutableLiveData<MutableList<E>>
 * and immediately mutate the MutableLiveData with a new MutableList value.
 *
 * @see MutableList
 */
@JvmName("mutateMutableList")
inline fun <E, reified T : MutableList<E>> MutableLiveData<T>.replaceFirst(
    newItem: E,
    noinline predicate: (item: E) -> Boolean
) {
    value = value?.replaceFirst(newItem, predicate) as T
}


/**
 * Apply the same function as MutableList.replaceFirst() but for a MutableLiveData<List<E>>
 * and immediately mutate the MutableLiveData with a new List value.
 *
 * @see MutableList
 * @see List
 */
@JvmName("mutateList")
inline fun <E, reified T : List<E>> MutableLiveData<T>.replaceFirst(
    newItem: E,
    noinline predicate: (item: E) -> Boolean
) {
    value = value?.toMutableList()?.replaceFirst(newItem, predicate)?.toList() as T
}

@JvmName("mapFirst")
inline fun <E, reified T : List<E>> MutableLiveData<T>.mapFirst(
    noinline map: (item: E) -> E,
    noinline predicate: (item: E) -> Boolean
) {
    val foundItem = value?.toMutableList()?.find(predicate) ?: return
    value = value
        ?.toMutableList()
        ?.replaceFirst(map(foundItem), predicate)
        ?.toList() as T
}

/**
 * Apply the same function as MutableList.add(value) but for a MutableLiveData<List<E>>
 * and immediately mutate the MutableLiveData with a new List value.
 *
 * @see MutableList
 * @see List
 */
@JvmName("addToList")
inline fun <E, reified T : List<E>> MutableLiveData<T>.add(newItem: E) {
    value = value?.toMutableList()?.apply { add(newItem) } as T
}

@JvmName("removeFromList")
inline fun <E, reified T : List<E>> MutableLiveData<T>.remove(item: E) {
    value = value?.toMutableList()?.apply { remove(item) } as T
}

/**
 * Apply the same function as MutableList.clear() but for a MutableLiveData<List<E>>
 * and immediately mutate the MutableLiveData with a new List value.
 *
 * @see MutableList
 * @see List
 */
@JvmName("clearFromList")
inline fun <E, reified T : List<E>> MutableLiveData<T>.clear() {
    value = emptyList<E>() as T
}


/**
 * Toggle a boolean value within a MutableLiveData.
 */
fun MutableLiveData<Boolean>.toggle() {
    value = !(value ?: false)
}


/**
 * Toggle a boolean value within a MutableLiveData but with postValue(newValue) instead.
 *
 * @see MutableLiveData.postValue
 */
fun MutableLiveData<Boolean>.postToggle() {
    postValue(!(value ?: false))
}


/**
 * Immediately set a boolean value within a MutableLiveData to true
 *
 * @see MutableLiveData
 */
fun MutableLiveData<Boolean>.toTrue() {
    value = true
}


/**
 * Immediately set a boolean value within a MutableLiveData to true
 *
 * @see MutableLiveData.postValue
 */
fun MutableLiveData<Boolean>.postTrue() {
    postValue(true)
}


/**
 * Immediately set a boolean value within a MutableLiveData to false
 *
 * @see MutableLiveData
 */
fun MutableLiveData<Boolean>.toFalse() {
    value = false
}

/**
 * Immediately set a boolean value within a MutableLiveData to false
 *
 * @see MutableLiveData.postValue
 */
fun MutableLiveData<Boolean>.postFalse() {
    postValue(false)
}

/**
 * Immediately return the value or empty string
 */
fun MutableLiveData<String>.orEmpty(): String {
    return value.orEmpty()
}