package com.mmb.happybox

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.mmb.happybox.cheerup.delete.DeleteItemFragment
import com.mmb.happybox.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DeleteItemFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun initializeFragmentTextsShouldBeDisplayed() {
        launchFragmentInHiltContainer<DeleteItemFragment>(
            fragmentArgs = bundleOf("id" to 1L)
        )

        onView(withText(R.string.are_you_sure)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withText(R.string.yes_delete)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun onClickOnDeleteShouldCallNavigationEvent() {
        val navController = mockk<NavController>()
        launchFragmentInHiltContainer<DeleteItemFragment>() {
            arguments = bundleOf("id" to 1L)
            Navigation.setViewNavController(requireView(), navController)
        }
        every {
            navController.navigateUp()
        } returns true

        onView(withText(R.string.yes_delete)).perform(click())

        verify(exactly = 1) {
            navController.navigateUp()
        }
    }

}