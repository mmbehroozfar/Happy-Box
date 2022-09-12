package com.mmb.moveis.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "happy_things")
data class HappyThingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "modified_at") val modifiedAt: Long,
)