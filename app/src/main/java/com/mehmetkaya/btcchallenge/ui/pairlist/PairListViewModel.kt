package com.mehmetkaya.btcchallenge.ui.pairlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetkaya.btcchallenge.domain.model.Ticker
import com.mehmetkaya.btcchallenge.domain.usecase.*
import com.mehmetkaya.btcchallenge.ui.pairlist.FavoriteListAdapter.FavoriteListItems.FavoriteItem
import com.mehmetkaya.btcchallenge.ui.pairlist.PairListAdapter.PairListItems.InfoItem
import com.mehmetkaya.btcchallenge.ui.pairlist.PairListAdapter.PairListItems.PairItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mehmetkaya.btcchallenge.ui.pairlist.FavoriteListAdapter.FavoriteListItems.InfoItem as FavoriteInfoItem

@HiltViewModel
class PairListViewModel @Inject constructor(
    private val showLoading: ShowLoading,
    private val showError: ShowError,
    private val fetchFavoriteList: FetchFavoriteList,
    private val fetchTickerList: FetchTickerList,
    private val favoritePair: FavoritePair,
    private val unFavoritePair: UnFavoritePair
) : ViewModel() {

    private val _uiState = MutableStateFlow(PairListUiState())
    val uiState: StateFlow<PairListUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<PairListUiEvent>()
    val uiEvent: SharedFlow<PairListUiEvent> = _uiEvent.asSharedFlow()

    init {
        fetchFavoriteList()
            .map {
                val favoriteItems = it.map(::FavoriteItem)
                _uiState.value.copy(favoriteItems = listOf(FavoriteInfoItem) + favoriteItems)
            }
            .onEach { state -> _uiState.emit(state).also { updatePairList(state.tickers) } }
            .launchIn(viewModelScope)
    }

    fun fetch() {
        viewModelScope.launch {
            showLoading(isLoading = true)

            runCatching { fetchTickerList() }
                .onSuccess { updatePairList(it) }
                .onFailure { showError(it) }
                .also { showLoading(isLoading = false) }
        }
    }

    fun onFavoriteClicked(pairItem: PairItem) {
        viewModelScope.launch {
            showLoading(isLoading = true)

            runCatching {
                if (pairItem.isFavorite) {
                    unFavoritePair(pairItem.ticker)
                } else {
                    favoritePair(pairItem.ticker)
                }
            }
                .onSuccess { }
                .onFailure { showError(it) }
                .also { showLoading(isLoading = false) }
        }
    }

    private fun updatePairList(tickers: List<Ticker>) {
        _uiState.update { state ->
            val pairItems = tickers.map { PairItem(it, state.isFavorite(it.pairNormalized)) }
            state.copy(pairItems = listOf(InfoItem) + pairItems)
        }
    }
}
