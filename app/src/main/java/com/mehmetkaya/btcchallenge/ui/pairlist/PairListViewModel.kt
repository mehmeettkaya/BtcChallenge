package com.mehmetkaya.btcchallenge.ui.pairlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PairListViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(PairListUiState())
    val uiState: StateFlow<PairListUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<PairListUiEvent>()
    val uiEvent: SharedFlow<PairListUiEvent> = _uiEvent.asSharedFlow()

}
