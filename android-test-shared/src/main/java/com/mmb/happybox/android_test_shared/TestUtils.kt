package com.mmb.happybox.android_test_shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

fun asyncRunTest(block: CoroutineScope.() -> Unit) = runTest {
    val deferred = async(SupervisorJob()) {
        block()
    }
    advanceUntilIdle()
    deferred.getCompletionExceptionOrNull()?.let {
        throw it
    }
}