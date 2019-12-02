package me.hafizdwp.jetpack_submission_final.ui.splash

import androidx.test.rule.ActivityTestRule
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.SplashscreenActivity
import me.hafizdwp.jetpack_submission_final.utils.isDisplayed
import me.hafizdwp.jetpack_submission_final.utils.withId
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
        withId(R.id.imageLogo)?.isDisplayed()
    }
}