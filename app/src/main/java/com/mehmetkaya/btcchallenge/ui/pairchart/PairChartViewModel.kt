package com.mehmetkaya.btcchallenge.ui.pairchart

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PairChartViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(PairChartUiState())
    val uiState: StateFlow<PairChartUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<PairChartUiEvent>()
    val uiEvent: SharedFlow<PairChartUiEvent> = _uiEvent.asSharedFlow()

}
