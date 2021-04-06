package app.prabudiworks.data.common

import com.google.gson.annotations.SerializedName

open class AppResponseMessage(
    @field:SerializedName("success") val success: Boolean? = null,
    @field:SerializedName("timestamp") val timestamp: Int? = null,
    @field:SerializedName("date") val date: String? = null,
    @field:SerializedName("base") val base: String? = null,
    @field:SerializedName("rates") val rates: Map<String, List<String>>? = null
) {

    fun dataAsList(): List<String> {
        val errorListing = mutableListOf<String>()
        rates?.onEach { datum -> errorListing.add(datum.value.joinToString(" and ")) }
        return errorListing
    }

}