package app.prabudiworks.data.service

import app.prabudiworks.data.common.AppResponse
import app.prabudiworks.data.common.AppResponseList
import app.prabudiworks.data.service.response.Converter
import retrofit2.Response
import retrofit2.http.GET

interface ConverterService {

    @GET("latest?access_key=f812aab9c4151d1e30ca95194ef7859a&symbols=BRL,CNY,EUR,IDR,JPY,GBP,USD")
    suspend fun getLatest(): Response<AppResponse<Converter>>

}