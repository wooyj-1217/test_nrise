package com.example.wooyj.network

import javax.inject.Inject

class NetworkRepository @Inject constructor( private val networkService: NetworkService) {
    suspend fun getList() = networkService.getList()
}