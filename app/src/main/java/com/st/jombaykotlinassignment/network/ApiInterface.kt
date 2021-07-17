package com.st.jombaykotlinassignment.network

import com.st.jombaykotlinassignment.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {
    @GET("/api/characters")
    fun fetchUser(@QueryMap param: Map<String, Int>): Call<List<UserModel>>
}