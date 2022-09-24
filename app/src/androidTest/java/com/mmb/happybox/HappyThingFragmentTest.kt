package com.mmb.happybox

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.mmb.happybox.android_test_shared.AppIdleResource
import com.mmb.happybox.cheerup.happything.HappyThingFragment
import com.mmb.happybox.utils.FakeData
import com.mmb.happybox.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HappyThingFragmentTest {

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
    fun initializeFragmentItemDataShouldBeDisplayed() {
        launchFragmentInHiltContainer<HappyThingFragment>(
            fragmentArgs = bundleOf("id" to 1L)
        )

        onView(withId(R.id.name_edt)).check(matches(withText(FakeData.item1)))
    }

    @Test
    fun typeNameShouldBeDisplayed() {
        launchFragmentInHiltContainer<HappyThingFragment>()

        onView(withId(R.id.name_edt)).perform(typeText(FakeData.item1))
        onView(withId(R.id.name_edt)).check(matches(withText(FakeData.item1)))
    }

    @Test
    fun emptyNameShouldDisableSaveButton() {
        launchFragmentInHiltContainer<HappyThingFragment>()

        onView(withId(R.id.save_btn)).check(
            matches(
                isNotEnabled()
            )
        )
    }

    @Test
    fun filledNameShouldEnableSaveButton() {
        launchFragmentInHiltContainer<HappyThingFragment>()

        onView(withId(R.id.name_edt)).perform(typeText(FakeData.item1))

        onView(withId(R.id.save_btn)).check(
            matches(
                isEnabled()
            )
        )
    }

    @Test
    fun clickOnSaveShouldCallNavigationEvent() {
        val navController = mockk<NavController>()
        launchFragmentInHiltContainer<HappyThingFragment>(
            fragmentArgs = bundleOf("id" to 1L)
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        every {
            navController.navigateUp()
        } returns true

        onView(withId(R.id.save_btn)).perform(click())

        verify(exactly = 1) {
            navController.navigateUp()
        }
    }

}