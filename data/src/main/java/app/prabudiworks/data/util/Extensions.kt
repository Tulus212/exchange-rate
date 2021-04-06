package app.prabudiworks.data.util

import app.prabudiworks.data.common.AppResponseMessage
import app.prabudiworks.data.common.exception.FallbackHttpException
import com.google.gson.Gson
import com.natpryce.Failure
import com.natpryce.Result
import com.natpryce.Success
import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.asResult(): Result<T, Exception> {
    try {
        val response = this

        if (response.isSuccessful) {
            response.body()?.let { body -> return Success(body) }
            // if body null, just pass it, return error responses
        }

        val errorBody = response.errorBody()?.string()
        return when (val errorResponseBody =
            Gson().fromJson(errorBody, AppResponseMessage::class.java)) {
            is AppResponseMessage -> Failure(FallbackHttpException(errorResponseBody))
            else -> Failure(FallbackHttpException(response.message()))
        }
    } catch (e: HttpException) {
        val errorBody = e.response()?.errorBody()?.string()
        val response = Gson().fromJson(errorBody, AppResponseMessage::class.java)
        return Failure(FallbackHttpException(response))
    } catch (e: Exception) {
        return Failure(e)
    }
}

fun <T> Map<String, T?>.removeNulls(): Map<String, T> {
    return filterValues { it != null } as Map<String, T>
}