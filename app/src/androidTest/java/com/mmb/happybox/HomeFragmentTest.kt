package com.mmb.happybox

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.mmb.happybox.android_test_shared.AppIdleResource
import com.mmb.happybox.cheerup.home.HomeFragment
import com.mmb.happybox.cheerup.home.HomeFragmentDirections
import com.mmb.happybox.utils.FakeData
import com.mmb.happybox.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.Matchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeFragmentTest {

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
    fun initializeFragmentItemsShouldBeDisplayed() {
        launchFragmentInHiltContainer<HomeFragment>()

        onView(withId(R.id.happy_things_up_txt)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(R.id.happy_things_down_txt)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(R.id.cheerup_slider)).check(matches(isDisplayed()))
    }

    @Test
    fun initializeFragmentItemsShouldHaveValues() {
        launchFragmentInHiltContainer<HomeFragment>()

        onView(withId(R.id.happy_things_up_txt)).check(matches(withText(containsString(FakeData.item1))))
        onView(withId(R.id.happy_things_down_txt)).check(matches(withText(containsString(FakeData.item1))))

        onView(withId(R.id.happy_things_up_txt)).check(matches(withText(containsString(FakeData.item2))))
        onView(withId(R.id.happy_things_down_txt)).check(matches(withText(containsString(FakeData.item2))))
    }

    @Test
    fun clickOnFaqShouldCallNavigationEvent() {
        val navController = mockk<NavController>()
        launchFragmentInHiltContainer<HomeFragment>() {
            Navigation.setViewNavController(requireView(), navController)
        }
        every {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToFaqFragment())
        } returns Unit

        onView(withId(R.id.faq_btn)).perform(click())

        verify(exactly = 1) {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToFaqFragment())
        }
    }

    @Test
    fun clickOnListShouldCallNavigationEvent() {
        val navController = mockk<NavController>()
        launchFragmentInHiltContainer<HomeFragment>() {
            Navigation.setViewNavController(requireView(), navController)
        }
        every {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToHappyThingsListFragment())
        } returns Unit

        onView(withId(R.id.happy_things_list_btn)).perform(click())

        verify(exactly = 1) {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToHappyThingsListFragment())
        }
    }

    @Test
    fun slideSliderShouldCallNavigationEvent() {
        val navController = mockk<NavController>()
        launchFragmentInHiltContainer<HomeFragment>() {
            Navigation.setViewNavController(requireView(), navController)
        }
        every {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToResultFragment())
        } returns Unit

        onView(withId(R.id.cheerup_slider)).perform(swipeRight())

        verify(exactly = 1) {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToResultFragment())
        }
    }

}