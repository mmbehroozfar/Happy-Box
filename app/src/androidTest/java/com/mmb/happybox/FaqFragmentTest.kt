package com.mmb.happybox

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.mmb.happybox.cheerup.faq.FaqFragment
import com.mmb.happybox.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class FaqFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun initializeFragmentTextsShouldBeDisplayed() {
        launchFragmentInHiltContainer<FaqFragment>()

        onView(withText(R.string.faq_description)).check(
            matches(
                isDisplayed()
            )
        )
    }

}