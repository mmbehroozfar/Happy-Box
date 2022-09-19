package com.mmb.happybox.data

import com.google.common.truth.Truth
import com.mmb.happybox.data.FakeData.DataModel.fakeItem1
import com.mmb.happybox.data.FakeData.DataModel.fakeItems
import com.mmb.happybox.data.daos.HappyThingDao
import com.mmb.happybox.data.datasources.HappyThingLocalDataSourceImpl
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

class HappyThingLocalDataSourceTest {

    private lateinit var happyThingLocalDataSource: HappyThingLocalDataSourceImpl

    @RelaxedMockK
    lateinit var happyThingDao: HappyThingDao

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        happyThingLocalDataSource = HappyThingLocalDataSourceImpl(happyThingDao)
    }

    @Test
    fun saveShouldCallDaoInsertMethod() = runTest {
        val savedItem = slot<HappyThingEntity>()
        coEvery {
            happyThingDao.insert(capture(savedItem))
        } returns Unit

        happyThingLocalDataSource.save(fakeItem1)

        coVerify(exactly = 1) { happyThingDao.insert(fakeItem1) }
        Truth.assertThat(fakeItem1).isEqualTo(savedItem.captured)
    }

    @Test
    fun deleteShouldCallDaoDeleteMethod() = runTest {
        val deleteItemId = slot<Long>()
        coEvery {
            happyThingDao.delete(capture(deleteItemId))
        } returns Unit

        happyThingLocalDataSource.delete(fakeItem1.id)

        coVerify(exactly = 1) { happyThingDao.delete(any()) }
        Truth.assertThat(fakeItem1.id).isEqualTo(deleteItemId.captured)
    }

    @Test
    fun getHappyThingShouldReturnItem() = runTest {
        coEvery {
            happyThingDao.getHappyThingEntity(fakeItem1.id)
        } returns fakeItem1

        val item = happyThingLocalDataSource.getHappyThing(fakeItem1.id)

        coVerify(exactly = 1) { happyThingDao.getHappyThingEntity(any()) }
        Truth.assertThat(item).isEqualTo(fakeItem1)
    }

    @Test
    fun getHappyThingsShouldReturnItems() = runTest {
        coEvery {
            happyThingDao.getHappyThingEntities()
        } returns flowOf(fakeItems)

        val items = happyThingLocalDataSource.getHappyThings().first()

        coVerify(exactly = 1) { happyThingDao.getHappyThingEntities() }
        Truth.assertThat(items).isEqualTo(fakeItems)
    }

    @Test
    fun getRandomHappyThingShouldReturnItem() = runTest {
        coEvery {
            happyThingDao.getRandomHappyThingEntity()
        } returns fakeItem1

        val item = happyThingLocalDataSource.getRandomHappyThing()

        coVerify(exactly = 1) { happyThingDao.getRandomHappyThingEntity() }
        Truth.assertThat(item).isEqualTo(fakeItem1)
    }

    @Test
    fun observeHappyThingsShouldReturnPagedItems() = runTest {
        happyThingLocalDataSource.observePagedHappyThings()

        coVerify(exactly = 1) { happyThingDao.observePagedHappyThings() }
    }

}