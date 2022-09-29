package com.mehmetkaya.btcchallenge.domain.usecase

import com.mehmetkaya.btcchallenge.data.repository.FavoriteRepository
import com.mehmetkaya.btcchallenge.domain.mapper.PairMapper
import com.mehmetkaya.btcchallenge.domain.model.Ticker
import com.mehmetkaya.btcchallenge.utils.mapWith
import javax.inject.Inject

class UpdatePair @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val pairMapper: PairMapper
) {

    suspend operator fun invoke(ticker: List<Ticker>) = with(ticker) {
        val favoriteEntities = ticker.map { it.mapWith(pairMapper) }
        favoriteRepository.updatePair(favoriteEntities)
    }
}
