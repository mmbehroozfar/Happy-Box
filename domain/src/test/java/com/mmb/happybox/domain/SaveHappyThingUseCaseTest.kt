package com.mmb.happybox.domain

import com.google.common.truth.Truth
import com.mmb.happybox.android_test_shared.CoroutineRule
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.domain.usecases.SaveHappyThingUseCase
import com.mmb.happybox.shared.Result
import com.mmb.happybox.shared.requireData
import com.mmb.happybox.shared.requireError
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SaveHappyThingUseCaseTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    private val coroutineDispatcher = UnconfinedTestDispatcher()

    private lateinit var saveHappyThingUseCase: SaveHappyThingUseCase

    @RelaxedMockK
    lateinit var happyThingRepository: HappyThingRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        saveHappyThingUseCase = SaveHappyThingUseCase(
            happyThingRepository = happyThingRepository,
            coroutineDispatcher = coroutineDispatcher
        )
    }

    @Test
    fun successInvokeShouldReturnSuccess() = runTest {
        coEvery {
            happyThingRepository.save(any())
        } returns Unit

        val result = saveHappyThingUseCase(FakeData.UiModel.fakeItem1)

        coVerify(exactly = 1) { happyThingRepository.save(any()) }
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat(result.requireData()).isEqualTo(Unit)
    }

    @Test
    fun failedInvokeShouldReturnError() = runTest {
        val failure = Exception()
        coEvery {
            happyThingRepository.save(any())
        } throws failure

        val result = saveHappyThingUseCase(FakeData.UiModel.fakeItem1)

        coVerify(exactly = 1) { happyThingRepository.save(any()) }
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat(result.requireError()).isEqualTo(failure)
    }

}