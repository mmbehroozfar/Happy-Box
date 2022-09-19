package com.mmb.happybox.domain

import com.google.common.truth.Truth
import com.mmb.happybox.android_test_shared.CoroutineRule
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.domain.usecases.GetRandomHappyThingUseCase
import com.mmb.happybox.shared.Result
import com.mmb.happybox.shared.requireData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetRandomHappyThingUseCaseTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    private val coroutineDispatcher = UnconfinedTestDispatcher()

    private lateinit var getRandomHappyThingUseCase: GetRandomHappyThingUseCase

    @RelaxedMockK
    lateinit var happyThingRepository: HappyThingRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getRandomHappyThingUseCase = GetRandomHappyThingUseCase(
            happyThingRepository = happyThingRepository,
            coroutineDispatcher = coroutineDispatcher
        )
    }

    @Test
    fun invokeShouldCallRepositoryMethod() = runTest {
        coEvery {
            happyThingRepository.getRandomHappyThing()
        } returns FakeData.DomainModel.fakeItem1

        val result = getRandomHappyThingUseCase()

        coVerify(exactly = 1) { happyThingRepository.getRandomHappyThing() }
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat(result.requireData().id).isEqualTo(FakeData.DomainModel.fakeItem1.id)
    }

}