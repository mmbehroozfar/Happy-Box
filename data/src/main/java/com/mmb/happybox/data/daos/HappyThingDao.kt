package com.mmb.happybox.data.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mmb.moveis.data.model.entities.HappyThingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HappyThingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(happyThingEntity: HappyThingEntity)

    @Query("DELETE FROM happy_things WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM happy_things WHERE id = :id")
    suspend fun getHappyThingEntity(id: Long): HappyThingEntity

    @Query("SELECT * FROM happy_things LIMIT 15")
    fun getHappyThingEntities(): Flow<List<HappyThingEntity>>

    @Query("SELECT * FROM happy_things")
    fun observePagedHappyThings(): PagingSource<Int, HappyThingEntity>

}