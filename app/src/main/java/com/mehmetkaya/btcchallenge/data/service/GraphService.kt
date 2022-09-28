package com.mehmetkaya.btcchallenge.data.service

import com.mehmetkaya.btcchallenge.data.model.KlineDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GraphService {

    @GET("v1/klines/history")
    suspend fun getKlineData(
        @Query("from") from: String,
        @Query("resolution") resolution: String,
        @Query("symbol") symbol: String,
        @Query("to") to: String
    ): KlineDataResponse
}
