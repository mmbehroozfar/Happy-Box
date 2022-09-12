package com.mmb.happybox.data.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mmb.moveis.data.model.entities.HappyThingEntity

@Dao
interface HappyThingDao {

    @Insert
    suspend fun insert(happyThingEntity: HappyThingEntity)

    @Update
    suspend fun update(happyThingEntity: HappyThingEntity)

    @Query("DELETE FROM happy_things WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM happy_things WHERE id = :id")
    suspend fun getHappyThingEntity(id: Long): HappyThingEntity

    @Query("SELECT * FROM happy_things LIMIT 15")
    suspend fun getHappyThingEntities(): List<HappyThingEntity>

    @Query("SELECT * FROM happy_things")
    fun observePagedMovies(): PagingSource<Int, HappyThingEntity>

}