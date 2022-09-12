package com.mmb.happybox.data.mappers

import com.mmb.happybox.domain.model.HappyThingDomain
import com.mmb.moveis.data.model.entities.HappyThingEntity

internal fun HappyThingEntity.toDomainModel() = HappyThingDomain(
    id = id,
    name = name,
)

internal fun List<HappyThingEntity>.toDomainModel() = map {
    it.toDomainModel()
}

internal fun HappyThingDomain.toEntity() = HappyThingEntity(
    id = id,
    name = name,
)