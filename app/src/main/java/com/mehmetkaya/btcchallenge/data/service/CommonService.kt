package com.mehmetkaya.btcchallenge.data.service

import com.mehmetkaya.btcchallenge.data.model.TickersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonService {

    @GET("v2/ticker")
    suspend fun getTickers(
        @Query("pairSymbol") pairSymbol: String
    ): TickersResponse
}
