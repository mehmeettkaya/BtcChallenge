package com.mehmetkaya.btcchallenge.domain.mapper

import com.mehmetkaya.btcchallenge.data.model.TickersResponse.TickerResponse
import com.mehmetkaya.btcchallenge.domain.model.Ticker
import com.mehmetkaya.btcchallenge.utils.Mapper
import com.mehmetkaya.btcchallenge.utils.orZero
import javax.inject.Inject

class TickerMapper @Inject constructor() : Mapper<TickerResponse, Ticker> {

    override fun map(input: TickerResponse): Ticker = with(input) {
        return Ticker(
            pair = pair.orEmpty(),
            pairNormalized = pairNormalized.orEmpty(),
            last = last.orZero(),
            volume = volume.orZero(),
            dailyPercent = dailyPercent.orZero(),
            numeratorSymbol = numeratorSymbol.orEmpty(),
        )
    }
}
