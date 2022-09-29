package com.mehmetkaya.btcchallenge.domain.mapper

import com.mehmetkaya.btcchallenge.data.database.FavoriteEntity
import com.mehmetkaya.btcchallenge.domain.model.Ticker
import com.mehmetkaya.btcchallenge.utils.Mapper
import javax.inject.Inject

class PairMapper @Inject constructor() : Mapper<Ticker, FavoriteEntity> {

    override fun map(input: Ticker): FavoriteEntity = with(input) {
        return FavoriteEntity(
            pairName = pairNormalized,
            last = last,
            dailyPercent = dailyPercent
        )
    }
}
