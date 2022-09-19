package com.mmb.happybox.domain

import androidx.paging.PagingData
import com.mmb.happybox.android_test_shared.CoroutineRule
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.domain.usecases.ObservePagedHappyThingsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ObservePagedHappyThingsUseCaseTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    private val coroutineDispatcher = UnconfinedTestDispatcher()

    private lateinit var observePagedHappyThingsUseCase: ObservePagedHappyThingsUseCase

    @RelaxedMockK
    lateinit var happyThingRepository: HappyThingRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        observePagedHappyThingsUseCase = ObservePagedHappyThingsUseCase(
            happyThingRepository = happyThingRepository,
            coroutineDispatcher = coroutineDispatcher
        )
    }

    @Test
    fun invokeShouldReturnPagingData() = runTest {
        coEvery {
            happyThingRepository.observePagedHappyThings(any())
        } returns flowOf(PagingData.from(FakeData.DomainModel.fakeItems))

        observePagedHappyThingsUseCase()

        coVerify(exactly = 1) { happyThingRepository.observePagedHappyThings(any()) }
    }

}