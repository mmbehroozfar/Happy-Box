package com.mmb.happybox.cheerup.home

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mmb.happybox.android_test_shared.CoroutineRule
import com.mmb.happybox.cheerup.FakeData
import com.mmb.happybox.domain.extensions.shuffleAndJoinToString
import com.mmb.happybox.domain.usecases.GetHappyThingsNamesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var getHappyThingsNamesUseCase: GetHappyThingsNamesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createViewModel() = HomeViewModel(
        getHappyThingsNamesUseCase = getHappyThingsNamesUseCase,
    )

    @Test
    fun initializingShouldCallUseCase() = runTest {
        coEvery {
            getHappyThingsNamesUseCase()
        } returns flowOf(FakeData.fakeItems.shuffleAndJoinToString { it.name })

        createViewModel()

        coVerify(exactly = 1) { getHappyThingsNamesUseCase() }
    }

    @Test
    fun initializingShouldSetItems() = runTest {
        coEvery {
            getHappyThingsNamesUseCase()
        } returns flowOf(FakeData.fakeItems.shuffleAndJoinToString { it.name })

        val viewModel = createViewModel()

        viewModel.happyThings.test {
            Truth.assertThat(awaitItem()).contains(FakeData.fakeItems.first().name)
        }
    }

    @Test
    fun onFabClickedShouldSetNavigateToFabScreen() = runTest {
        val viewModel = createViewModel()

        viewModel.navigateToFaqScreen.test {
            viewModel.onFaqClicked()
            Truth.assertThat(awaitItem()).isEqualTo(Unit)
        }
    }

    @Test
    fun onListClickedShouldSetNavigateToListScreen() = runTest {
        val viewModel = createViewModel()

        viewModel.navigateToHappyThingsListScreen.test {
            viewModel.onHappyThingsListClicked()
            Truth.assertThat(awaitItem()).isEqualTo(Unit)
        }
    }

}