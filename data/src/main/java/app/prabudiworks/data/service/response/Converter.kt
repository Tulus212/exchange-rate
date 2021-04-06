package app.prabudiworks.data.service.response

import com.google.gson.annotations.SerializedName

data class Converter(

    @field:SerializedName("BRL")
    val BRL: Double? = null,

    @field:SerializedName("CNY")
    val CNY: Double? = null,

    @field:SerializedName("EUR")
    val EUR: Double? = null,

    @field:SerializedName("IDR")
    val IDR: Double? = null,

    @field:SerializedName("JPY")
    val JPY: Double? = null,

    @field:SerializedName("GBP")
    val GBP: Double? = null,

    @field:SerializedName("USD")
    val USD: Double? = null,

)