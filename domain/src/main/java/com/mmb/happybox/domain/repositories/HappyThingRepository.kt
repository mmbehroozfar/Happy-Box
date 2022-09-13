package com.mmb.happybox.domain.repositories

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mmb.happybox.domain.model.HappyThingDomain
import kotlinx.coroutines.flow.Flow

interface HappyThingRepository {

    suspend fun save(happyThingDomain: HappyThingDomain)

    suspend fun delete(id: Long)

    suspend fun getHappyThing(id: Long): HappyThingDomain

    fun getHappyThings(): Flow<List<HappyThingDomain>>

    fun observePagedHappyThings(pagingConfig: PagingConfig): Flow<PagingData<HappyThingDomain>>

    suspend fun getRandomHappyThing(): HappyThingDomain

}