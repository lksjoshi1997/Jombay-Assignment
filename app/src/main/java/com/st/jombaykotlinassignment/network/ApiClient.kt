package com.st.jombaykotlinassignment.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.breakingbadapi.com")
        .client(getClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiInterface(): ApiInterface = retrofit.create(ApiInterface::class.java)

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

}