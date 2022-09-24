package com.mmb.happybox.android_test_shared

import android.os.Handler
import android.os.Looper
import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DelayedIdlingResource(
    private val tag: String,
    private val delay: Long,
) : IdlingResource {

    @Volatile
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    @Volatile
    private var counter = AtomicInteger(0)

    @Volatile
    private var isIdle = AtomicBoolean(true)

    override fun getName(): String = tag

    override fun isIdleNow(): Boolean = isIdle.get()

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

    fun increment() {
        counter.getAndIncrement()
        if (isIdleNow)
            isIdle.set(false)
    }

    fun decrement() {
        if (isIdleNow) {
            return
        }
        if (counter.decrementAndGet() == 0) {
            GlobalScope.launch {
                delay(delay)
                isIdle.set(true)
                resourceCallback?.onTransitionToIdle()
            }
            Handler(Looper.getMainLooper())
                .postDelayed(
                    {
                        //  resourceCallback?.onTransitionToIdle()
                    },
                    delay
                )
        }
    }

}