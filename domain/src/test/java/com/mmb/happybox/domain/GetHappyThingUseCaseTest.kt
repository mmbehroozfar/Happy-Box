package com.mmb.happybox.domain

import com.google.common.truth.Truth
import com.mmb.happybox.android_test_shared.CoroutineRule
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.domain.usecases.GetHappyThingUseCase
import com.mmb.happybox.shared.Result
import com.mmb.happybox.shared.requireData
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

class GetHappyThingUseCaseTest {

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    private val coroutineDispatcher = UnconfinedTestDispatcher()

    private lateinit var getHappyThingUseCase: GetHappyThingUseCase

    @RelaxedMockK
    lateinit var happyThingRepository: HappyThingRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getHappyThingUseCase = GetHappyThingUseCase(
            happyThingRepository = happyThingRepository,
            coroutineDispatcher = coroutineDispatcher
        )
    }

    @Test
    fun invokeShouldCallRepositoryMethod() = runTest {
        val id = FakeData.DomainModel.fakeItem1.id
        val deleteItemId = slot<Long>()
        coEvery {
            happyThingRepository.getHappyThing(capture(deleteItemId))
        } returns FakeData.DomainModel.fakeItem1

        val result = getHappyThingUseCase(id)

        coVerify(exactly = 1) { happyThingRepository.getHappyThing(id) }
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat(result.requireData().id).isEqualTo(id)
    }

}