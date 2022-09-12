package com.mmb.happybox.domain.baseusecases

import com.mmb.happybox.shared.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class ResultUseCase<in T, out R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(params: T): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(params).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: T): R
}
