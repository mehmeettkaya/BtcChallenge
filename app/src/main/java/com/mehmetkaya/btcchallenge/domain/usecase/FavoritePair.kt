package com.mehmetkaya.btcchallenge.domain.usecase

import com.mehmetkaya.btcchallenge.data.database.FavoriteEntity
import com.mehmetkaya.btcchallenge.data.repository.FavoriteRepository
import com.mehmetkaya.btcchallenge.domain.model.Ticker
import javax.inject.Inject

class FavoritePair @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(ticker: Ticker) = with(ticker) {
        val favoriteEntity = FavoriteEntity(pairNormalized, last, dailyPercent)
        favoriteRepository.favoritePair(favoriteEntity)
    }
}
