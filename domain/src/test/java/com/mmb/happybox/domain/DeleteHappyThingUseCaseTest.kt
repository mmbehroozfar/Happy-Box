package com.mmb.happybox.domain

import com.google.common.truth.Truth
import com.mmb.happybox.android_test_shared.CoroutineRule
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.domain.usecases.DeleteHappyThingUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeleteHappyThingUseCaseTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    private val coroutineDispatcher = UnconfinedTestDispatcher()

    private lateinit var deleteHappyThingUseCase: DeleteHappyThingUseCase

    @RelaxedMockK
    lateinit var happyThingRepository: HappyThingRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        deleteHappyThingUseCase = DeleteHappyThingUseCase(
            happyThingRepository = happyThingRepository,
            coroutineDispatcher = coroutineDispatcher
        )
    }

    @Test
    fun invokeShouldCallRepositoryDeleteMethod() = runTest {
        val deleteItemId = slot<Long>()
        coEvery {
            happyThingRepository.delete(capture(deleteItemId))
        } returns Unit

        deleteHappyThingUseCase(1L)

        coVerify(exactly = 1) { happyThingRepository.delete(any()) }
        Truth.assertThat(1L).isEqualTo(deleteItemId.captured)
    }

}