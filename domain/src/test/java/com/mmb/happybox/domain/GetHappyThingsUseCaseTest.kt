package com.mmb.happybox.domain

import com.google.common.truth.Truth
import com.mmb.happybox.android_test_shared.CoroutineRule
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.domain.usecases.GetHappyThingsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetHappyThingsUseCaseTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    private val coroutineDispatcher = UnconfinedTestDispatcher()

    private lateinit var getHappyThingsUseCase: GetHappyThingsUseCase

    @RelaxedMockK
    lateinit var happyThingRepository: HappyThingRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getHappyThingsUseCase = GetHappyThingsUseCase(
            happyThingRepository = happyThingRepository,
            coroutineDispatcher = coroutineDispatcher
        )
    }

    @Test
    fun invokeShouldCallRepositoryMethod() = runTest {
        coEvery {
            happyThingRepository.getHappyThings()
        } returns flowOf(FakeData.DomainModel.fakeItems)

        val items = getHappyThingsUseCase().first()

        coVerify(exactly = 1) { happyThingRepository.getHappyThings() }
        Truth.assertThat(items.first().id).isEqualTo(FakeData.UiModel.fakeItems.first().id)
    }

}