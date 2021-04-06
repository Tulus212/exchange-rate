package app.prabudiworks.exchangerate.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.prabudiworks.common.app.AppViewModel
import app.prabudiworks.common.state.AppList
import app.prabudiworks.data.service.ConverterRepository
import app.prabudiworks.data.service.response.Converter
import app.prabudiworks.exchangerate.home.model.ConverterResponse
import com.natpryce.onFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mConverterRepository: ConverterRepository) :
    AppViewModel() {

    private val mConverterData by lazy { MutableLiveData<Converter>() }
    private val converterData by lazy { MutableLiveData<List<ConverterResponse>>() }
    val converterList: LiveData<List<ConverterResponse>> = converterData

    init {
        fetchConverter()
    }

    private fun fetchConverter() {
        viewModelScope.launch {
            mConverterData.value = mConverterRepository.getConverter()
                .onFailure {
                    ConverterResponse.dummies()
                    return@launch
                }
            converterData.value = listOf(
                ConverterResponse(1, "BRL", mConverterData.value!!.BRL, "BRAZIL"),
                ConverterResponse(2, "CNY", mConverterData.value!!.CNY, "CHINA"),
                ConverterResponse(3, "EUR", mConverterData.value!!.EUR, "FRANCE"),
                ConverterResponse(4, "EUR", mConverterData.value!!.EUR, "GERMANY"),
                ConverterResponse(5, "IDR", mConverterData.value!!.IDR, "INDONESIA"),
                ConverterResponse(6, "EUR", mConverterData.value!!.EUR, "ITALY"),
                ConverterResponse(7, "JPY", mConverterData.value!!.JPY, "JAPAN"),
                ConverterResponse(8, "EUR", mConverterData.value!!.EUR, "SPAIN"),
                ConverterResponse(9, "GBP", mConverterData.value!!.GBP, "UNITED KINGDOM"),
                ConverterResponse(10, "USD", mConverterData.value!!.USD, "UNITED STATES")
            )
        }
    }
}