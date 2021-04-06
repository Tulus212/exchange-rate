package app.prabudiworks.common.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.natpryce.Result
import com.natpryce.onFailure

class AppList<T> {

    val state by lazy { MutableLiveData<AppListState>() }
    val data by lazy {
        Transformations.map(state) {
            if (it is AppListState.Success<*>) it.data as List<T>
            else emptyList()
        }
    }

    val isLoading by lazy { Transformations.map(state) { it is AppListState.Loading } }
    val isEmpty by lazy { Transformations.map(state) { it is AppListState.Empty } }
    val isFailed by lazy { Transformations.map(state) { it is AppListState.Failure } }

    suspend fun load(block: suspend () -> Result<List<T>, Exception>) {
        state.postValue(AppListState.Loading)
        val result = block().onFailure {
            state.postValue(AppListState.Failure(it.reason))
            return
        }
        state.postValue(
            if (result.isNotEmpty()) AppListState.Success(result)
            else AppListState.Empty
        )
    }

}

sealed class AppListState {
    object Loading : AppListState()
    object Empty : AppListState()
    class Success<T>(val data: List<T>) : AppListState()
    class Failure(val e: Exception) : AppListState()
}