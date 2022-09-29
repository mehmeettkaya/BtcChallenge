package com.mehmetkaya.btcchallenge.ui.pairlist

sealed class PairListUiEvent {
    data class NavigateToPairChart(
        val pairName: String
    ) : PairListUiEvent()
}
