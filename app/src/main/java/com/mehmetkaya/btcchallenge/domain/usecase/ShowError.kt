package com.mehmetkaya.btcchallenge.domain.usecase

import com.mehmetkaya.btcchallenge.data.exception.Exceptions.HttpException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowError @Inject constructor() {

    private val error = MutableSharedFlow<String>()

    operator fun invoke(): SharedFlow<String> = error.asSharedFlow()

    suspend operator fun invoke(exception: Throwable) {
        val message = when (exception) {
            is HttpException -> exception.errorMessage
            else -> exception.message.toString()
        }
        error.emit(message)
    }
}
