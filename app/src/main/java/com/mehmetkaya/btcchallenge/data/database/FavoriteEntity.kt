package com.mehmetkaya.btcchallenge.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    val pairName: String,
    val last: Double,
    val dailyPercent: Double
)
