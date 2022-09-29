package com.mehmetkaya.btcchallenge.data.repository

import androidx.annotation.WorkerThread
import com.mehmetkaya.btcchallenge.data.database.FavoriteDao
import com.mehmetkaya.btcchallenge.data.database.FavoriteEntity
import com.mehmetkaya.btcchallenge.data.exception.HandleException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val handleException: HandleException
) {

    fun getAllFavorites(): Flow<List<FavoriteEntity>> {
        return runCatching {
            favoriteDao.getAllFavorites()
        }.getOrElse { throw handleException(it) }
    }

    @WorkerThread
    suspend fun favoritePair(favoriteEntity: FavoriteEntity) {
        runCatching {
            favoriteDao.insertFavorite(favoriteEntity)
        }.onFailure { throw handleException(it) }
    }

    @WorkerThread
    suspend fun unFavoritePair(pairName: String) {
        runCatching {
            favoriteDao.deleteFavorite(pairName)
        }.onFailure { throw handleException(it) }
    }
}
