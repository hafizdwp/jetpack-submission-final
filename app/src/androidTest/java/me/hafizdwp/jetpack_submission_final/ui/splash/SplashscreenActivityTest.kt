package me.hafizdwp.jetpack_submission_final.ui.splash

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.SplashscreenActivity
import org.junit.Rule
import org.junit.Test

/**
 * @author hafizdwp
 * 02/12/2019
 */
class SplashscreenActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(SplashscreenActivity::class.java)

    @Test
    fun checkImageLogo() {
        Espresso.onView(ViewMatchers.withId(R.id.imageLogo)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}