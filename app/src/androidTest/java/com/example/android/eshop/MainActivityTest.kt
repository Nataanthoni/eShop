package com.example.android.eshop

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // Given on the home screen
        val scenario = launchFragmentInContainer<LoginFragment>(Bundle(), R.style.Theme_Eshop)
        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

    }

    @Test
    fun splashScreen_loadsLoginFragment() {

        // When - App name exists
        onView(withId(R.id.name_label)).check(matches(withText("eShop")))
    }

    @Test
    fun clickNextButton_navigateToProductGridFragment() {

        onView(withId(R.id.username_edit_text)).perform(typeText("john"), closeSoftKeyboard())

        onView(withId(R.id.password_edit_text)).perform(typeText("johndoe123"), closeSoftKeyboard())
        // THEN  - Verify that we navigate to the add screen
        // -- TBD
    }

}