package com.mmb.happybox.data.database

import com.mmb.happybox.data.daos.HappyThingDao

interface HappyBoxDatabase {
    fun happyThingsDao(): HappyThingDao
}
