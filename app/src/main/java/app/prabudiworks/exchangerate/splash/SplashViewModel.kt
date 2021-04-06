package app.prabudiworks.exchangerate.splash

import androidx.lifecycle.viewModelScope
import app.prabudiworks.common.app.AppViewModel
import app.prabudiworks.common.lifecycle.MutableLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : AppViewModel() {

    val openMainPageEvent by lazy { MutableLiveEvent(Unit) }

    fun decidesNext() {
        viewModelScope.launch {
            openMainPageEvent.set()
        }
    }
}