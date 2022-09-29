package com.mehmetkaya.btcchallenge.ui.pairlist

import com.mehmetkaya.btcchallenge.domain.model.Ticker
import com.mehmetkaya.btcchallenge.ui.pairlist.FavoriteListAdapter.FavoriteListItem
import com.mehmetkaya.btcchallenge.ui.pairlist.PairListAdapter.PairListItem

data class PairListUiState(
    val favoriteItems: List<FavoriteListItem> = emptyList(),
    val pairItems: List<PairListItem> = emptyList()
) {

    val tickers: List<Ticker>
        get() = pairItems.map { it.ticker }

    val isFavoriteLayoutVisible: Boolean
        get() = favoriteItems.isNotEmpty()

    fun isFavorite(pairName: String): Boolean {
        return favoriteItems.any { it.favorite.pairName == pairName }
    }
}
