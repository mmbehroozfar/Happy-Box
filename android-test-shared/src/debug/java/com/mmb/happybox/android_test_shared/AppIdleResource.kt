package com.mmb.happybox.android_test_shared

object AppIdleResource {

    private const val TAG = "AppIdleResource"
    private const val IDLE_DELAY = 500L

    @JvmField
    val idlingResource = DelayedIdlingResource(tag = TAG, delay = IDLE_DELAY)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }

}

