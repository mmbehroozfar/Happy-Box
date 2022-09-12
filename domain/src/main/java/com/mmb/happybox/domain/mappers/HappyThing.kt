package com.mmb.happybox.domain.mappers

import com.mmb.happybox.domain.model.HappyThingDomain
import com.mmb.happybox.model.HappyThing


internal fun HappyThingDomain.toUiModel() = HappyThing(
    id = id,
    name = name,
)

internal fun List<HappyThingDomain>.toUiModel() = map {
    it.toUiModel()
}

internal fun HappyThing.toDomainModel() = HappyThingDomain(
    id = id,
    name = name,
)