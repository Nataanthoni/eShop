package com.example.android.eshop

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ProductGridFragmentTest {

    @Before
    fun setUp() {
        // Given on the products screen
        val scenario = launchFragmentInContainer<ProductGridFragment>(Bundle(), R.style.Theme_Eshop)
        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
    }

    @Test
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun productGrid_loadsProducts() = runBlockingTest  {

        // When #1 - product exists
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText("Vagabond sack")),
                    ViewActions.click()
                ))
    }
}
