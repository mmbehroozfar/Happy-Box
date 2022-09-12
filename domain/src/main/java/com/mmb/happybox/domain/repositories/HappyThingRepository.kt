package com.mmb.happybox.domain.repositories

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mmb.happybox.domain.model.HappyThing
import kotlinx.coroutines.flow.Flow

interface HappyThingRepository {

    suspend fun save(happyThing: HappyThing)

    suspend fun update(happyThing: HappyThing)

    suspend fun delete(id: Long)

    suspend fun getHappyThing(id: Long): HappyThing

    suspend fun getHappyThings(): List<HappyThing>

    fun observePagedHappyThings(pagingConfig: PagingConfig): Flow<PagingData<HappyThing>>

}