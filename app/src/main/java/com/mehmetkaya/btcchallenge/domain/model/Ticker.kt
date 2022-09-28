package com.mehmetkaya.btcchallenge.domain.model

data class Ticker(
    val pair: String,
    val pairNormalized: String,
    val last: Double,
    val volume: Double,
    val dailyPercent: Double,
    val numeratorSymbol: String
)
