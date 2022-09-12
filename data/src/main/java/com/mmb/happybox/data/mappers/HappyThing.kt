package com.mmb.happybox.data.mappers

import com.mmb.happybox.domain.model.HappyThing
import com.mmb.moveis.data.model.entities.HappyThingEntity

internal fun HappyThingEntity.toDomainModel() = HappyThing(
    id = id,
    name = name,
)

internal fun List<HappyThingEntity>.toDomainModel() = map {
    it.toDomainModel()
}

internal fun HappyThing.toEntity() = HappyThingEntity(
    id = id,
    name = name,
)