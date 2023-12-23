package com.voidhash.hearthstonecodex.test

import android.view.Gravity
import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.presentation.activity.MainActivity
import com.voidhash.hearthstonecodex.presentation.adapter.CardAdapter
import com.voidhash.hearthstonecodex.presentation.fragment.MainFragment
import com.voidhash.hearthstonecodex.util.TestUtil
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class MainFragmentInstrumentedTest {

    @JvmField
    @Rule
    var activityRule: ActivityScenarioRule<MainActivity?>? = ActivityScenarioRule (MainActivity::class.java)

    private lateinit var fragmentScenario: FragmentScenario<MainFragment>
    private lateinit var fragment: MainFragment

    @Test
    fun `Test standard collection recycler`(){

        //Open and close the drawer navigation
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())

        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(matches(isOpen(Gravity.LEFT)))
            .perform(DrawerActions.close())

        fragmentScenario = FragmentScenario.launchInContainer(MainFragment::class.java)
        fragmentScenario.onFragment {
            fragment = it
        }

        //Launch Main Fragment and wait 3s to load data for recycle views
//        FragmentScenario.launchInContainer(MainFragment::class.java)
//        Thread.sleep(3000)

        val recyclerViewIds = listOf(R.id.rcl_standard, R.id.rcl_wild, R.id.rcl_card_back)
        TestUtil.registerIdlingResourceForRecyclerViews(fragment, recyclerViewIds)


        //Test standard recycler view
        Espresso.onView(ViewMatchers.withId(R.id.rcl_standard))
            .perform(RecyclerViewActions.scrollToLastPosition<CardAdapter.ViewHolder>())

        Espresso.onView(ViewMatchers.withId(R.id.rcl_standard))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CardAdapter.ViewHolder>(0, click()))

        //Test wild recycler view
        Espresso.onView(ViewMatchers.withId(R.id.rcl_wild))
            .perform(RecyclerViewActions.scrollToLastPosition<CardAdapter.ViewHolder>())

        Espresso.onView(ViewMatchers.withId(R.id.rcl_wild))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CardAdapter.ViewHolder>(0, click()))

        //Scroll app to the bottom
        Espresso.onView(ViewMatchers.withId(R.id.main_scroll))
            .perform(ViewActions.swipeUp(), click())

        //Test card back recycler view
        Espresso.onView(ViewMatchers.withId(R.id.rcl_card_back))
            .perform(RecyclerViewActions.scrollToLastPosition<CardAdapter.ViewHolder>())

        Espresso.onView(ViewMatchers.withId(R.id.rcl_card_back))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CardAdapter.ViewHolder>(0, click()))

        //Scroll app to the top
        Espresso.onView(ViewMatchers.withId(R.id.main_scroll))
            .perform(ViewActions.swipeDown())


        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        fragmentScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        Espresso.onView(withId(R.id.buttonCollection)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.collectionFragment)

        TestUtil.unregisterIdlingResourceForRecyclerViews(fragment, recyclerViewIds)
    }

    @After
    fun tearDown() {
        fragmentScenario.close()
    }
}