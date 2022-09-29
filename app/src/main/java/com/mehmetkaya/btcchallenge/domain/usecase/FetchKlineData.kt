package com.mehmetkaya.btcchallenge.domain.usecase

import com.mehmetkaya.btcchallenge.data.repository.GraphRepository
import com.mehmetkaya.btcchallenge.domain.mapper.KlineDataMapper
import com.mehmetkaya.btcchallenge.domain.model.KlineData
import com.mehmetkaya.btcchallenge.utils.mapWith
import com.mehmetkaya.btcchallenge.utils.toEpochSecond
import java.util.*
import java.util.Calendar.DATE
import javax.inject.Inject

class FetchKlineData @Inject constructor(
    private val graphRepository: GraphRepository,
    private val klineDataMapper: KlineDataMapper
) {

    suspend operator fun invoke(symbol: String): KlineData {
        val toDate = Date()
        val fromDate = Calendar.getInstance().apply {
            time = toDate
            add(DATE, -ONE_DAY)
        }

        val response = graphRepository.getKlineData(
            from = fromDate.time.toEpochSecond(),
            resolution = RESOLUTION,
            symbol = symbol,
            to = toDate.toEpochSecond()
        )
        return response.mapWith(klineDataMapper)
    }

    companion object {
        private const val ONE_DAY = 1
        private const val RESOLUTION = 60
    }
}
