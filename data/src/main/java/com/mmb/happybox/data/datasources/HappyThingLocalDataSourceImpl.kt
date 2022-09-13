package com.mmb.happybox.data.datasources

import androidx.paging.PagingSource
import com.mmb.happybox.data.daos.HappyThingDao
import com.mmb.moveis.data.model.entities.HappyThingEntity
import javax.inject.Inject

class HappyThingLocalDataSourceImpl @Inject constructor(
    private val happyThingDao: HappyThingDao,
) : HappyThingLocalDataSource {

    override suspend fun save(happyThingEntity: HappyThingEntity) =
        happyThingDao.insert(happyThingEntity)

    override suspend fun delete(id: Long) = happyThingDao.delete(id)

    override suspend fun getHappyThing(id: Long) =
        happyThingDao.getHappyThingEntity(id)

    override fun getHappyThings() = happyThingDao.getHappyThingEntities()

    override fun observePagedHappyThings(): PagingSource<Int, HappyThingEntity> =
        happyThingDao.observePagedHappyThings()
}
