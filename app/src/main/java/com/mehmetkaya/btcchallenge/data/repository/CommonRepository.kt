package com.mehmetkaya.btcchallenge.data.repository

import com.mehmetkaya.btcchallenge.data.exception.HandleException
import com.mehmetkaya.btcchallenge.data.model.TickersResponse.TickerResponse
import com.mehmetkaya.btcchallenge.data.service.CommonService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommonRepository @Inject constructor(
    private val commonService: CommonService,
    private val handleException: HandleException
) {

    suspend fun getTickers(pairSymbol: String): List<TickerResponse> {
        return runCatching {
            commonService.getTickers(pairSymbol).data.orEmpty()
        }.getOrElse { throw handleException(it) }
    }
}
