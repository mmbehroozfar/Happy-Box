package com.mmb.happybox.domain.mappers

import com.mmb.happybox.domain.model.HappyThing as HappyThingDomain
import com.mmb.happybox.model.HappyThing as HappyThingUi

internal fun HappyThingDomain.toUiModel() = HappyThingDomain(
    id = id,
    name = name,
)

internal fun List<HappyThingDomain>.toUiModel() = map {
    it.toUiModel()
}

internal fun HappyThingUi.toDomainModel() = HappyThingDomain(
    id = id,
    name = name,
)