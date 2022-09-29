package com.mehmetkaya.btcchallenge.domain.usecase

import com.mehmetkaya.btcchallenge.data.repository.FavoriteRepository
import com.mehmetkaya.btcchallenge.domain.mapper.FavoriteMapper
import com.mehmetkaya.btcchallenge.domain.model.Favorite
import com.mehmetkaya.btcchallenge.utils.mapWith
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchFavoriteList @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val favoriteMapper: FavoriteMapper
) {

    operator fun invoke(): Flow<List<Favorite>> {
        val entities = favoriteRepository.getAllFavorites()
        return entities.map { it.map { entity -> entity.mapWith(favoriteMapper) } }
    }
}
