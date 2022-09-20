package com.mmb.happybox.cheerup.delete

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mmb.happybox.android_test_shared.CoroutineRule
import com.mmb.happybox.domain.usecases.DeleteHappyThingUseCase
import com.mmb.happybox.shared.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeleteItemViewModelTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @RelaxedMockK
    lateinit var deleteHappyThingUseCase: DeleteHappyThingUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun createViewModel(id: Long) = DeleteItemViewModel(
        deleteHappyThingUseCase = deleteHappyThingUseCase,
        savedStateHandle = SavedStateHandle(mapOf("id" to id)),
    )

    @Test
    fun onDeleteItemShouldCallUseCase() = runTest {
        val deleteItemId = 1L
        val viewModel = createViewModel(deleteItemId)

        coEvery {
            deleteHappyThingUseCase(deleteItemId)
        } returns Result.Success(Unit)

        viewModel.onDeleteItemClicked()

        coVerify(exactly = 1) { deleteHappyThingUseCase(deleteItemId) }
    }

    @Test
    fun onDeleteItemShouldEmitCloseDialog() = runTest {
        val deleteItemId = 1L
        val viewModel = createViewModel(deleteItemId)

        coEvery {
            deleteHappyThingUseCase(deleteItemId)
        } returns Result.Success(Unit)

        viewModel.onCloseDialog.test {
            viewModel.onDeleteItemClicked()
            Truth.assertThat(awaitItem()).isEqualTo(Unit)
        }
    }

}