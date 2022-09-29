package com.mehmetkaya.btcchallenge.di.module

import android.content.Context
import androidx.room.Room
import com.mehmetkaya.btcchallenge.data.database.FavoriteDao
import com.mehmetkaya.btcchallenge.data.database.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideFavoriteDatabase(@ApplicationContext appContext: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoriteDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoriteDao {
        return favoriteDatabase.favoriteDao()
    }

    companion object {
        private const val DB_NAME = "FAVORITE_DB"
    }
}
