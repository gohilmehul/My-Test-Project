package com.example.myproject.DatabaseInterface


import com.example.myproject.Model.DrugsResponse
import com.example.myproject.Network.DrugsApi
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    val topdrugsApi: DrugsApi
) {

    suspend fun getTopHeadlines(): Response<DrugsResponse> {
        return topdrugsApi.getDrugsApilines()
    }

}