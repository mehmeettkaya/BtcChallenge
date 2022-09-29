package com.mehmetkaya.btcchallenge.ui.pairchart

import com.github.mikephil.charting.data.Entry
import com.mehmetkaya.btcchallenge.domain.model.KlineData

data class PairChartUiState(
    val symbol: String = "",
    val klineData: KlineData? = null
) {

    val entries: List<Entry>
        get() {
            if (klineData == null) return emptyList()

            return klineData.timestamp.zip(klineData.close)
                .map { (x, y) -> Entry(x.toFloat(), y.toFloat()) }
        }
}
