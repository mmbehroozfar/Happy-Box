package com.mmb.happybox.data

import androidx.paging.PagingConfig
import com.google.common.truth.Truth
import com.mmb.happybox.data.datasources.HappyThingLocalDataSource
import com.mmb.happybox.data.repositories.HappyThingRepositoryImpl
import com.mmb.moveis.data.model.entities.HappyThingEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HappyThingRepositoryTest {

    private lateinit var happyThingRepository: HappyThingRepositoryImpl

    @RelaxedMockK
    lateinit var happyThingLocalDataSource: HappyThingLocalDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        happyThingRepository = HappyThingRepositoryImpl(happyThingLocalDataSource)
    }

    @Test
    fun saveShouldCallDatasourceSaveMethod() = runTest {
        val savedItem = slot<HappyThingEntity>()
        coEvery {
            happyThingLocalDataSource.save(capture(savedItem))
        } returns Unit

        happyThingRepository.save(FakeData.DomainModel.fakeItem1)

        coVerify(exactly = 1) { happyThingLocalDataSource.save(any()) }
        Truth.assertThat(FakeData.DomainModel.fakeItem1.id).isEqualTo(savedItem.captured.id)
    }

    @Test
    fun deleteShouldCallDatasourceDeleteMethod() = runTest {
        val deletedItemId = slot<Long>()
        coEvery {
            happyThingLocalDataSource.delete(capture(deletedItemId))
        } returns Unit

        happyThingRepository.delete(FakeData.DataModel.fakeItem1.id)

        coVerify(exactly = 1) { happyThingLocalDataSource.delete(any()) }
        Truth.assertThat(FakeData.DataModel.fakeItem1.id).isEqualTo(deletedItemId.captured)
    }

    @Test
    fun getHappyThingShouldReturnItem() = runTest {
        val id = FakeData.DataModel.fakeItem1.id
        coEvery {
            happyThingLocalDataSource.getHappyThing(id)
        } returns FakeData.DataModel.fakeItem1

        val item = happyThingRepository.getHappyThing(id)

        coVerify(exactly = 1) { happyThingLocalDataSource.getHappyThing(any()) }
        Truth.assertThat(item.id).isEqualTo(FakeData.DataModel.fakeItem1.id)
    }

    @Test
    fun getHappyThingsShouldReturnItems() = runTest {
        coEvery {
            happyThingLocalDataSource.getHappyThings()
        } returns flowOf(FakeData.DataModel.fakeItems)

        val items = happyThingLocalDataSource.getHappyThings().first()

        coVerify(exactly = 1) { happyThingRepository.getHappyThings() }
        Truth.assertThat(items.first().id).isEqualTo(FakeData.DomainModel.fakeItems.first().id)
    }

    @Test
    fun getRandomHappyThingShouldReturnItem() = runTest {
        coEvery {
            happyThingLocalDataSource.getRandomHappyThing()
        } returns FakeData.DataModel.fakeItem1

        val item = happyThingRepository.getRandomHappyThing()

        coVerify(exactly = 1) { happyThingLocalDataSource.getRandomHappyThing() }
        Truth.assertThat(item.id).isEqualTo(FakeData.DomainModel.fakeItem1.id)
    }

    @Test
    fun observeHappyThingsShouldReturnPagedItems() = runTest {
        val pagingConfig = PagingConfig(
            pageSize = 10,
            prefetchDistance = 3,
            enablePlaceholders = true,
            initialLoadSize = 15
        )
        happyThingRepository.observePagedHappyThings(pagingConfig).first()

        coVerify(exactly = 1) { happyThingLocalDataSource.observePagedHappyThings() }
    }

}