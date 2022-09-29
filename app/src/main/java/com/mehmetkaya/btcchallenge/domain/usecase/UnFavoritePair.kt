package com.mehmetkaya.btcchallenge.domain.usecase

import com.mehmetkaya.btcchallenge.data.repository.FavoriteRepository
import com.mehmetkaya.btcchallenge.domain.model.Ticker
import javax.inject.Inject

class UnFavoritePair @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(ticker: Ticker) = with(ticker) {
        favoriteRepository.unFavoritePair(pairNormalized)
    }
}
