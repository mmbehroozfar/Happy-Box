package com.mmb.happybox.cheerup.result

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mmb.happybox.android_test_shared.CoroutineRule
import com.mmb.happybox.cheerup.FakeData
import com.mmb.happybox.domain.usecases.GetRandomHappyThingUseCase
import com.mmb.happybox.shared.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ResultViewModelTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var getRandomHappyThingUseCase: GetRandomHappyThingUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createViewModel() = ResultViewModel(
        getRandomHappyThingUseCase = getRandomHappyThingUseCase,
    )

    @Test
    fun initializingShouldCallUseCase() = runTest {
        coEvery {
            getRandomHappyThingUseCase()
        } returns Result.Success(FakeData.fakeItem1)

        createViewModel()

        coVerify(exactly = 1) { getRandomHappyThingUseCase() }
    }

    @Test
    fun initializingShouldSetItem() = runTest {
        coEvery {
            getRandomHappyThingUseCase()
        } returns Result.Success(FakeData.fakeItem1)

        val viewModel = createViewModel()

        viewModel.happyThing.test {
            Truth.assertThat(awaitItem()).isEqualTo(FakeData.fakeItem1.name)
        }
    }

    @Test
    fun clickOnDoThatShouldSetDialogClose() = runTest {
        coEvery {
            getRandomHappyThingUseCase()
        } returns Result.Error(Exception())

        val viewModel = createViewModel()

        viewModel.onCloseDialog.test {
            viewModel.onDoThatClicked()
            Truth.assertThat(awaitItem()).isEqualTo(Unit)
        }
    }

}