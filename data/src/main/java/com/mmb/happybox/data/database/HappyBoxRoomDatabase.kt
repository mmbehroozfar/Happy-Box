package com.mmb.happybox.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmb.moveis.data.model.entities.HappyThingEntity

@Database(
    entities = [
        HappyThingEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class HappyBoxRoomDatabase : RoomDatabase(), HappyBoxDatabase