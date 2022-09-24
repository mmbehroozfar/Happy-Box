package com.mmb.happybox

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    DeleteItemFragmentTest::class,
    FaqFragmentTest::class,
    HappyThingsListFragmentTest::class,
    HappyThingFragmentTest::class,
    HomeFragmentTest::class,
    ResultFragmentTest::class,
)
class FragmentsTestsSuite