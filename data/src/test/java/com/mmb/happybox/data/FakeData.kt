package com.mmb.happybox.data

import com.mmb.happybox.domain.model.HappyThingDomain
import com.mmb.moveis.data.model.entities.HappyThingEntity

internal object FakeData {

    object DomainModel {
        val fakeItem1 = HappyThingDomain(0, "Buy an Ice Cream")
        val fakeItem2 = HappyThingDomain(1, "Buy a Pizza")
        val fakeItems = listOf(fakeItem1, fakeItem2)
    }

    object DataModel {
        val fakeItem1 = HappyThingEntity(0, "Buy an Ice Cream")
        val fakeItem2 = HappyThingEntity(1, "Buy a Pizza")
        val fakeItems = listOf(fakeItem1, fakeItem2)
    }

}