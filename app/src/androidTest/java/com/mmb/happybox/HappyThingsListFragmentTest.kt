package com.mmb.happybox

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.mmb.happybox.android_test_shared.AppIdleResource
import com.mmb.happybox.cheerup.happythingslist.HappyThingsListFragment
import com.mmb.happybox.cheerup.happythingslist.HappyThingsListFragmentDirections
import com.mmb.happybox.cheerup.happythingslist.HappyThingsPagingAdapter
import com.mmb.happybox.utils.FakeData
import com.mmb.happybox.utils.clickChildViewWithId
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
@MediumTest
class HappyThingsListFragmentTest {

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
        launchFragmentInHiltContainer<HappyThingsListFragment>()

        onView(withText(FakeData.item1)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withText(FakeData.item2)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun clickOnAddItemsShouldNavigationEvent() {
        val navController = mockk<NavController>()
        launchFragmentInHiltContainer<HappyThingsListFragment>() {
            Navigation.setViewNavController(requireView(), navController)
        }
        every {
            navController.navigate(HappyThingsListFragmentDirections.actionHappyThingsListFragmentToHappyThingFragment())
        } returns Unit

        onView(withId(R.id.add_fab)).perform(click())

        verify(exactly = 1) {
            navController.navigate(HappyThingsListFragmentDirections.actionHappyThingsListFragmentToHappyThingFragment())
        }
    }

    @Test
    fun clickOnEditItemsShouldNavigationEvent() {
        val navController = mockk<NavController>()
        launchFragmentInHiltContainer<HappyThingsListFragment>() {
            Navigation.setViewNavController(requireView(), navController)
        }
        every {
            navController.navigate(
                HappyThingsListFragmentDirections.actionHappyThingsListFragmentToHappyThingFragment(
                    1L
                )
            )
        } returns Unit

        onView(withId(R.id.items_rv))
            .perform(
                actionOnItemAtPosition<HappyThingsPagingAdapter.HappyThingViewHolder>(
                    0,
                    clickChildViewWithId(R.id.edit_btn)
                )
            )

        verify(exactly = 1) {
            navController.navigate(
                HappyThingsListFragmentDirections.actionHappyThingsListFragmentToHappyThingFragment(
                    1L
                )
            )
        }
    }

    @Test
    fun clickOnDeleteItemsShouldNavigationEvent() {
        val navController = mockk<NavController>()
        launchFragmentInHiltContainer<HappyThingsListFragment>() {
            Navigation.setViewNavController(requireView(), navController)
        }
        every {
            navController.navigate(
                HappyThingsListFragmentDirections.actionHappyThingsListFragmentToDeleteItemFragment(
                    1L
                )
            )
        } returns Unit

        onView(withId(R.id.items_rv))
            .perform(
                actionOnItemAtPosition<HappyThingsPagingAdapter.HappyThingViewHolder>(
                    0,
                    clickChildViewWithId(R.id.delete_btn)
                )
            )

        verify(exactly = 1) {
            navController.navigate(
                HappyThingsListFragmentDirections.actionHappyThingsListFragmentToDeleteItemFragment(
                    1L
                )
            )
        }
    }

}