package com.mmb.happybox.domain.baseusecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

abstract class NoParamsFlowUseCase<out R>(private val coroutineDispatcher: CoroutineDispatcher) {

    operator fun invoke(): Flow<R> {
        return execute()
    }

    @Throws(RuntimeException::class)
    protected abstract fun execute(): Flow<R>
}
