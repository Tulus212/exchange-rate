package app.prabudiworks.exchangerate.home.model

import app.prabudiworks.common.util.SimpleDiffUtil

data class ConverterResponse(
    val id: Int,
    val currency:String? = null,
    val value: Double? = null,
    val country: String? = null,
) {

    companion object {

        val DIFF_UTIL = object : SimpleDiffUtil<ConverterResponse>() {
            override fun getIdentifier(item: ConverterResponse): Any {
                return item.id
            }
        }

        fun dummies() = listOf(
            ConverterResponse(1, "BRL", 1.0, "BRAZIL"),
            ConverterResponse(2, "CNY", 1.0, "CHINA"),
            ConverterResponse(3, "EUR", 1.0, "FRANCE"),
            ConverterResponse(4, "EUR", 1.0, "GERMANY"),
            ConverterResponse(5, "IDR", 1.0, "INDONESIA"),
            ConverterResponse(6, "EUR", 1.0, "ITALY"),
            ConverterResponse(7, "JPY", 1.0, "JAPAN"),
            ConverterResponse(8, "EUR", 1.0, "SPAIN"),
            ConverterResponse(9, "GBP", 1.0, "UNITED KINGDOM"),
            ConverterResponse(10, "USD", 1.0, "UNITED STATES")
        )

    }

}