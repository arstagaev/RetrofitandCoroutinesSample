package com.revolve44.postmakermassive.models.alfa.beta


import com.google.gson.annotations.SerializedName

data class FiveDaysForecast(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("message")
    val message: Int,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list: List<Weather>,
    @SerializedName("city")
    val city: City
)