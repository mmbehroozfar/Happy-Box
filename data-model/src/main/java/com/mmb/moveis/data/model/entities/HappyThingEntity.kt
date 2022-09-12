package com.mmb.moveis.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "happy_things")
data class HappyThingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
)