package app.prabudiworks.data.common

import com.google.gson.annotations.SerializedName
import com.natpryce.Failure
import com.natpryce.Result
import com.natpryce.Success

open class AppResponse<T>(
    @field:SerializedName("success") val success: Boolean? = null,
    @field:SerializedName("timestamp") val timestamp: Int? = null,
    @field:SerializedName("date") val date: String? = null,
    @field:SerializedName("base") val base: String? = null,
    @field:SerializedName("rates") val rates: T?
) {

    fun takeData(): Result<T, Exception> {
        return if (rates == null) Failure(IllegalStateException("Unavailable response data")) else Success(rates)
    }

}