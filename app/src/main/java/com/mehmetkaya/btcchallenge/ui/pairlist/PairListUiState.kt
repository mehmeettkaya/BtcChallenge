package com.mehmetkaya.btcchallenge.ui.pairlist

import com.mehmetkaya.btcchallenge.domain.model.Ticker
import com.mehmetkaya.btcchallenge.ui.pairlist.FavoriteListAdapter.FavoriteListItems
import com.mehmetkaya.btcchallenge.ui.pairlist.FavoriteListAdapter.FavoriteListItems.FavoriteItem
import com.mehmetkaya.btcchallenge.ui.pairlist.PairListAdapter.PairListItems
import com.mehmetkaya.btcchallenge.ui.pairlist.PairListAdapter.PairListItems.PairItem

data class PairListUiState(
    val favoriteItems: List<FavoriteListItems> = emptyList(),
    val pairItems: List<PairListItems> = emptyList()
) {

    val tickers: List<Ticker>
        get() = pairItems
            .filterIsInstance<PairItem>()
            .map { it.ticker }

    val isFavoriteLayoutVisible: Boolean
        get() = favoriteItems
            .filterIsInstance<FavoriteItem>()
            .isNotEmpty()

    fun isFavorite(pairName: String): Boolean {
        return favoriteItems
            .filterIsInstance<FavoriteItem>()
            .any { it.favorite.pairName == pairName }
    }
}
