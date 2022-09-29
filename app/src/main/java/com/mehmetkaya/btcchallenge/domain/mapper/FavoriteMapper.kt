package com.mehmetkaya.btcchallenge.domain.mapper

import com.mehmetkaya.btcchallenge.data.database.FavoriteEntity
import com.mehmetkaya.btcchallenge.domain.model.Favorite
import com.mehmetkaya.btcchallenge.utils.Mapper
import javax.inject.Inject

class FavoriteMapper @Inject constructor() : Mapper<FavoriteEntity, Favorite> {

    override fun map(input: FavoriteEntity): Favorite = with(input) {
        return Favorite(
            pairName = pairName,
            last = last,
            dailyPercent = dailyPercent,
        )
    }
}
