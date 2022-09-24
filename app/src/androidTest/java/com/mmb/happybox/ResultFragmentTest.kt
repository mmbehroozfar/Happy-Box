package com.mmb.happybox

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.mmb.happybox.android_test_shared.AppIdleResource
import com.mmb.happybox.cheerup.result.ResultFragment
import com.mmb.happybox.utils.FakeData
import com.mmb.happybox.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.Matchers.anyOf
import org.hamcrest.Matchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ResultFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(AppIdleResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(AppIdleResource.idlingResource)
    }

    @Test
    fun initializeFragmentTextsShouldBeDisplayed() {
        launchFragmentInHiltContainer<ResultFragment>()

        onView(withId(R.id.result_txt)).check(
            matches(
                withText(
                    anyOf(
                        containsString(
                            FakeData.item1
                        ), containsString(
                            FakeData.item2
                        )
                    )
                )
            )
        )
    }

    @Test
    fun clickOnDoShouldCallNavigationEvent() {
        val navController = mockk<NavController>()
        launchFragmentInHiltContainer<ResultFragment>() {
            Navigation.setViewNavController(requireView(), navController)
        }

        every {
            navController.navigateUp()
        } returns true

        onView(withId(R.id.do_btn)).perform(click())

        verify(exactly = 1) {
            navController.navigateUp()
        }
    }

}