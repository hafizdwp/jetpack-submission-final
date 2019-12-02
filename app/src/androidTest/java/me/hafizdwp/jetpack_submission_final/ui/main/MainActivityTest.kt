package me.hafizdwp.jetpack_submission_final.ui.main

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.ui.MainActivity
import me.hafizdwp.jetpack_submission_final.utils.EspressoUtils
import org.junit.Rule
import org.junit.Test

/**
 * @author hafizdwp
 * 16/11/2019
 */
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkBottomNavIsDisplayedAndChecked() {
        Espresso.onView(ViewMatchers.withId(R.id.bottomNav)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            check(ViewAssertions.matches(EspressoUtils.hasCheckedItem(R.id.bnav_main)))
        }
    }
}