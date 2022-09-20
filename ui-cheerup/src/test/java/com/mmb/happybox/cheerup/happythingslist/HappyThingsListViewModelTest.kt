package com.mmb.happybox.cheerup.happythingslist

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mmb.happybox.android_test_shared.CoroutineRule
import com.mmb.happybox.cheerup.FakeData
import com.mmb.happybox.domain.usecases.ObservePagedHappyThingsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HappyThingsListViewModelTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var observePagedHappyThingsUseCase: ObservePagedHappyThingsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createViewModel() = HappyThingsListViewModel(
        observePagedHappyThingsUseCase = observePagedHappyThingsUseCase,
    )

    @Test
    fun initializingShouldCallUseCase() = runTest {
        coEvery {
            observePagedHappyThingsUseCase()
        } returns flowOf(PagingData.from(FakeData.fakeItems))

        val viewModel = createViewModel()

        viewModel.happyThings.test {
            awaitItem()
            awaitComplete()
        }
    }

    @Test
    fun onAddClickedShouldSetNavigateHappyThingsScreen() = runTest {
        val viewModel = createViewModel()

        viewModel.navigateToHappyThingScreen.test {
            viewModel.onAddItemClicked()
            Truth.assertThat(awaitItem()).isEqualTo(Unit)
        }
    }
}