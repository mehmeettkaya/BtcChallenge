package com.mehmetkaya.btcchallenge.ui.pairchart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetkaya.btcchallenge.domain.usecase.FetchKlineData
import com.mehmetkaya.btcchallenge.domain.usecase.ShowError
import com.mehmetkaya.btcchallenge.domain.usecase.ShowLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PairChartViewModel @Inject constructor(
    private val showLoading: ShowLoading,
    private val showError: ShowError,
    private val fetchKlineData: FetchKlineData
) : ViewModel() {

    private val _uiState = MutableStateFlow(PairChartUiState())
    val uiState: StateFlow<PairChartUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<PairChartUiEvent>()
    val uiEvent: SharedFlow<PairChartUiEvent> = _uiEvent.asSharedFlow()

    fun init(pairName: String) {
        _uiState.update { it.copy(symbol = pairName) }
    }

    fun fetch() {
        viewModelScope.launch {
            showLoading(isLoading = true)

            runCatching { fetchKlineData(_uiState.value.symbol) }
                .onSuccess { _uiState.update { state -> state.copy(klineData = it) } }
                .onFailure { showError(it) }
                .also { showLoading(isLoading = false) }
        }
    }
}
