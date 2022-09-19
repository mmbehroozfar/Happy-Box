package com.mmb.happybox

import com.google.common.truth.Truth
import com.mmb.happybox.shared.Result
import org.junit.Test

class ResultTest {

    @Test
    fun successResultShouldHaveData() {
        val data = 0
        val result = Result.Success(data)

        Truth.assertThat(result.data).isEqualTo(data)
    }

    @Test
    fun errorResultShouldHaveException() {
        val exception = IllegalArgumentException()
        val result = Result.Error(exception)

        Truth.assertThat(result.error).isEqualTo(exception)
    }

}