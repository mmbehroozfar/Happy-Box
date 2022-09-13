package com.mmb.happybox.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mmb.happybox.data.datasources.HappyThingLocalDataSource
import com.mmb.happybox.data.mappers.toDomainModel
import com.mmb.happybox.data.mappers.toEntity
import com.mmb.happybox.domain.extensions.mapList
import com.mmb.happybox.domain.extensions.mapPagingData
import com.mmb.happybox.domain.model.HappyThingDomain
import com.mmb.happybox.domain.repositories.HappyThingRepository
import javax.inject.Inject

class HappyThingRepositoryImpl @Inject constructor(
    private val happyThingLocalDataSource: HappyThingLocalDataSource,
) : HappyThingRepository {

    override suspend fun save(happyThingDomain: HappyThingDomain) =
        happyThingLocalDataSource.save(happyThingDomain.toEntity())

    override suspend fun delete(id: Long) = happyThingLocalDataSource.delete(id)

    override suspend fun getHappyThing(id: Long) =
        happyThingLocalDataSource.getHappyThing(id).toDomainModel()

    override fun getHappyThings() =
        happyThingLocalDataSource.getHappyThings().mapList { it.toDomainModel() }

    override fun observePagedHappyThings(pagingConfig: PagingConfig) = Pager(
        config = pagingConfig,
        pagingSourceFactory = { happyThingLocalDataSource.observePagedHappyThings() }
    ).flow.mapPagingData {
        it.toDomainModel()
    }

}