package com.mmb.happybox.domain

import com.mmb.happybox.domain.model.HappyThingDomain
import com.mmb.happybox.model.HappyThing

internal object FakeData {

    object DomainModel {
        val fakeItem1 = HappyThingDomain(0, "Buy an Ice Cream")
        val fakeItem2 = HappyThingDomain(1, "Buy a Pizza")
        val fakeItems = listOf(fakeItem1, fakeItem2)
    }

    object UiModel {
        val fakeItem1 = HappyThing(0, "Buy an Ice Cream")
        val fakeItem2 = HappyThing(1, "Buy a Pizza")
        val fakeItems = listOf(fakeItem1, fakeItem2)
    }

}