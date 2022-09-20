package com.mmb.happybox.cheerup.happything

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.mmb.happybox.android_test_shared.CoroutineRule
import com.mmb.happybox.cheerup.FakeData
import com.mmb.happybox.domain.usecases.GetHappyThingUseCase
import com.mmb.happybox.domain.usecases.SaveHappyThingUseCase
import com.mmb.happybox.shared.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HappyThingViewModelTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var getHappyThingUseCase: GetHappyThingUseCase

    @RelaxedMockK
    lateinit var saveHappyThingUseCase: SaveHappyThingUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createViewModel(id: Long) = HappyThingViewModel(
        getHappyThingUseCase = getHappyThingUseCase,
        saveHappyThingUseCase = saveHappyThingUseCase,
        savedStateHandle = SavedStateHandle(mapOf("id" to id)),
    )

    @Test
    fun initializingShouldCallUseCase() = runTest {
        val itemId = 1L
        coEvery {
            getHappyThingUseCase(itemId)
        } returns Result.Success(FakeData.fakeItem1)

        createViewModel(itemId)

        coVerify(exactly = 1) { getHappyThingUseCase(itemId) }
    }

    @Test
    fun initializingShouldGetHappyThingItem() = runTest {
        val itemId = 1L
        coEvery {
            getHappyThingUseCase(itemId)
        } returns Result.Success(FakeData.fakeItem1)

        val viewModel = createViewModel(itemId)

        val item = viewModel.happyThing.first()

        Truth.assertThat(item).isEqualTo(FakeData.fakeItem1)
    }

}