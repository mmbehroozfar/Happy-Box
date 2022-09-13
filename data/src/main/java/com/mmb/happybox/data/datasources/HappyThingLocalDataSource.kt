package com.mmb.happybox.data.datasources

import androidx.paging.PagingSource
import com.mmb.moveis.data.model.entities.HappyThingEntity
import kotlinx.coroutines.flow.Flow

interface HappyThingLocalDataSource {

    suspend fun save(happyThingEntity: HappyThingEntity)

    suspend fun delete(id: Long)

    suspend fun getHappyThing(id: Long): HappyThingEntity

    fun getHappyThings(): Flow<List<HappyThingEntity>>

    fun observePagedHappyThings(): PagingSource<Int, HappyThingEntity>

    suspend fun getRandomHappyThing(): HappyThingEntity

}