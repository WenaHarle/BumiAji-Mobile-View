package com.example.bumiajimobileview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("cbr/api_get_data.php")
    fun getComments(@Query("limit") limit: String): Call<List<Comments>>
}

