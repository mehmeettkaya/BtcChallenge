package com.mehmetkaya.btcchallenge.domain.usecase

import com.mehmetkaya.btcchallenge.data.repository.GraphRepository
import com.mehmetkaya.btcchallenge.domain.mapper.KlineDataMapper
import com.mehmetkaya.btcchallenge.domain.model.KlineData
import com.mehmetkaya.btcchallenge.utils.mapWith
import javax.inject.Inject

class FetchKlineData @Inject constructor(
    private val graphRepository: GraphRepository,
    private val klineDataMapper: KlineDataMapper
) {

    suspend operator fun invoke(
        from: String,
        resolution: String,
        symbol: String,
        to: String
    ): KlineData {
        val response = graphRepository.getKlineData(from, resolution, symbol, to)
        return response.mapWith(klineDataMapper)
    }
}
