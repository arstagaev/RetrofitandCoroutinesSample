package com.revolve44.postmakermassive.api

import com.revolve44.postmakermassive.utils.Constants.Companion.BASE_URL
import com.revolve44.postmakermassive.utils.Constants.Companion.BASE_URL2
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {

    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client =OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        private val retrofit2 by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create()) // may be a Moshi
                .client(client)
                .build()
        }

        val apiAlpha by lazy { retrofit.create(MassiveAPI::class.java)}
        val apiBeta by lazy { retrofit.create(MassiveAPI::class.java)}

    }


}