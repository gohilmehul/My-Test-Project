package com.example.myproject.Network


import com.example.myproject.Model.DrugsResponse
import retrofit2.Response
import retrofit2.http.GET

interface DrugsApi {

    @GET("v3/8886b1fb-94f8-42d6-aec3-75a00b577d2c")
    suspend fun getDrugsApilines(
    ): Response<DrugsResponse>
}