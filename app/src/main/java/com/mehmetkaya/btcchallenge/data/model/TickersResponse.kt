package com.mehmetkaya.btcchallenge.data.model

import com.google.gson.annotations.SerializedName

data class TickersResponse(
    @SerializedName("data")
    val data: List<TickerResponse>?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("code")
    val code: Int?
) {

    data class TickerResponse(
        @SerializedName("pair")
        val pair: String?,
        @SerializedName("pairNormalized")
        val pairNormalized: String?,
        @SerializedName("timestamp")
        val timestamp: Long?,
        @SerializedName("last")
        val last: Double?,
        @SerializedName("high")
        val high: Double?,
        @SerializedName("low")
        val low: Double?,
        @SerializedName("bid")
        val bid: Double?,
        @SerializedName("ask")
        val ask: Double?,
        @SerializedName("open")
        val open: Double?,
        @SerializedName("volume")
        val volume: Double?,
        @SerializedName("average")
        val average: Double?,
        @SerializedName("daily")
        val daily: Double?,
        @SerializedName("dailyPercent")
        val dailyPercent: Double?,
        @SerializedName("denominatorSymbol")
        val denominatorSymbol: String?,
        @SerializedName("numeratorSymbol")
        val numeratorSymbol: String?,
        @SerializedName("order")
        val order: Int?
    )
}
