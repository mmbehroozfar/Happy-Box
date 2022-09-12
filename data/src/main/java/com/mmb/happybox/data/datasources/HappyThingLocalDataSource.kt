package com.mmb.happybox.data.datasources

import androidx.paging.PagingSource
import com.mmb.moveis.data.model.entities.HappyThingEntity

interface HappyThingLocalDataSource {

    suspend fun save(happyThingEntity: HappyThingEntity)

    suspend fun delete(id: Long)

    suspend fun getHappyThing(id: Long): HappyThingEntity

    suspend fun getHappyThings(): List<HappyThingEntity>

    fun observePagedHappyThings(): PagingSource<Int, HappyThingEntity>

}