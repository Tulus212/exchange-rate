package app.prabudiworks.common.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.natpryce.Result
import com.natpryce.onFailure

class AppData<T> {

    val state by lazy { MutableLiveData<AppListState>() }
    val data by lazy {
        Transformations.map(state) {
            if (it is AppDataState.Success<*>) it.data as T
            else null
        }
    }

    val isLoading by lazy { Transformations.map(state) { it is AppListState.Loading } }
    val isFailed by lazy { Transformations.map(state) { it is AppListState.Failure } }

    suspend fun load(block: suspend () -> Result<List<T>, Exception>) {
        state.postValue(AppListState.Loading)
        val result = block().onFailure {
            state.postValue(AppListState.Failure(it.reason))
            return
        }
        state.postValue(AppListState.Success(result))
    }

}

sealed class AppDataState {
    object Loading : AppDataState()
    class Success<T>(val data: T) : AppDataState()
    class Failure(val e: Exception) : AppDataState()
}