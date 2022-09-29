package com.mehmetkaya.btcchallenge.domain.usecase

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowLoading @Inject constructor() {

    private val loading = MutableSharedFlow<Boolean>()

    operator fun invoke(): SharedFlow<Boolean> = loading.asSharedFlow()

    suspend operator fun invoke(isLoading: Boolean) {
        loading.emit(isLoading)
    }
}
