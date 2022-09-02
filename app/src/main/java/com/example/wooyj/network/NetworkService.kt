package com.example.wooyj.network

import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET(ApiUri.API_DATA4)
    suspend fun getList() : Response<MyRanking>

}