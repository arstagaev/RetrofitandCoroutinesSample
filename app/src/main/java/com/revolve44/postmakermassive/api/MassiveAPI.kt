package com.revolve44.postmakermassive.api

import com.revolve44.postmakermassive.models.alfa.CurrentForecast
import com.revolve44.postmakermassive.utils.Constants.Companion.AMOUNT_PARAM
import com.revolve44.postmakermassive.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MassiveAPI {
    // http://api.openweathermap.org/data/2.5/forecast?lat=55.5&lon=37.5&cnt=40&appid=ac79fea59e9d15377b787a610a29b784
    // http://api.openweathermap.org/data/2.5/weather?lat=55.5&lon=37.5&appid=ac79fea59e9d15377b787a610a29b784

    //for 1 day
    @GET("data/2.5/weather")
    suspend fun getAlfaRequest(
        @Query("lat")
        lat: Double = 55.5,
        @Query("lon")
        lon: Double = 37.5,
        @Query("appid")
        apiKey: String = API_KEY

    ) : Response<CurrentForecast>

    // for 5 days
    @GET("data/2.5/forecast")
    suspend fun getBetaRequest(
            @Query("lat")
            lat: Double = 55.5,
            @Query("lon")
            lon: Double = 37.5,
            @Query("cnt")
            cnt: Int = AMOUNT_PARAM,
            @Query("appid")
            apiKey: String = API_KEY
    ) : Response<CurrentForecast>

}