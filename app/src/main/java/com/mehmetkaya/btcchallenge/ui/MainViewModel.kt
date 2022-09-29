package com.mehmetkaya.btcchallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetkaya.btcchallenge.domain.usecase.ShowError
import com.mehmetkaya.btcchallenge.domain.usecase.ShowLoading
import com.mehmetkaya.btcchallenge.ui.MainUiEvent.ShowErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    showLoading: ShowLoading,
    showError: ShowError
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<MainUiEvent>()
    val uiEvent: SharedFlow<MainUiEvent> = _uiEvent.asSharedFlow()

    init {
        showLoading()
            .map { _uiState.value.copy(isLoading = it) }
            .onEach { _uiState.emit(it) }
            .launchIn(viewModelScope)

        showError()
            .map { ShowErrorMessage(it) }
            .onEach { _uiEvent.emit(it) }
            .launchIn(viewModelScope)
    }
}
