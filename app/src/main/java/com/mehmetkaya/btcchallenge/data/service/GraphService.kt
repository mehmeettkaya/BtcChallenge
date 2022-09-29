package com.mehmetkaya.btcchallenge.data.service

import com.mehmetkaya.btcchallenge.data.model.KlineDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GraphService {

    @GET("v1/klines/history")
    suspend fun getKlineData(
        @Query("from") from: Long,
        @Query("resolution") resolution: Int,
        @Query("symbol") symbol: String,
        @Query("to") to: Long
    ): KlineDataResponse
}
