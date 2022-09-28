package com.mehmetkaya.btcchallenge.data.repository

import com.mehmetkaya.btcchallenge.data.exception.HandleException
import com.mehmetkaya.btcchallenge.data.model.KlineDataResponse
import com.mehmetkaya.btcchallenge.data.service.GraphService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GraphRepository @Inject constructor(
    private val graphService: GraphService,
    private val handleException: HandleException
) {

    suspend fun getKlineData(
        from: String,
        resolution: String,
        symbol: String,
        to: String
    ): KlineDataResponse {
        return runCatching {
            graphService.getKlineData(from, resolution, symbol, to)
        }.getOrElse { throw handleException(it) }
    }
}
