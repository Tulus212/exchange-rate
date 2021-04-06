package app.prabudiworks.data.service

import app.prabudiworks.data.service.response.Converter
import app.prabudiworks.data.util.asResult
import com.natpryce.Result
import com.natpryce.onFailure
import javax.inject.Inject

class ConverterRepository @Inject constructor(
    private val mConverterService: ConverterService
) {

    suspend fun getConverter(): Result<Converter, Exception> {
        return mConverterService
            .getLatest()
            .asResult()
            .onFailure { return it }
            .takeData()
    }



}