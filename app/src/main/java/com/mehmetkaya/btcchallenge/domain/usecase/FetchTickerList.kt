package com.mehmetkaya.btcchallenge.domain.usecase

import com.mehmetkaya.btcchallenge.data.repository.CommonRepository
import com.mehmetkaya.btcchallenge.domain.mapper.TickerMapper
import com.mehmetkaya.btcchallenge.domain.model.Ticker
import com.mehmetkaya.btcchallenge.utils.mapWith
import javax.inject.Inject

class FetchTickerList @Inject constructor(
    private val commonRepository: CommonRepository,
    private val tickerMapper: TickerMapper,
    private val updatePair: UpdatePair
) {

    suspend operator fun invoke(pairSymbol: String = ""): List<Ticker> {
        val response = commonRepository.getTickers(pairSymbol)
        return response.map { it.mapWith(tickerMapper) }
            .also { updatePair(it) }
    }
}
