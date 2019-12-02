package me.hafizdwp.jetpack_submission_final.ui.main

import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.ui.MainFragment
import me.hafizdwp.jetpack_submission_final.utils.test.SingleFragmentActivity
import me.hafizdwp.jetpack_submission_final.utils.isDisplayed
import me.hafizdwp.jetpack_submission_final.utils.matchWithText
import me.hafizdwp.jetpack_submission_final.utils.withId
import me.hafizdwp.jetpack_submission_final.utils.ViewPagerItemCountAssertion
import me.hafizdwp.jetpack_submission_final.utils.test.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author hafizdwp
 * 02/12/2019
 */
class MainFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)

    private val mainFragment = MainFragment()
    val mAppTitle = "Movreak"


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
        activityRule.activity.setFragment(mainFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovie() {

        // Toolbar
        withId(R.id.toolbar)?.isDisplayed()
        withId(R.id.imgMovreak)?.isDisplayed()
        withId(R.id.textToolbarTitle)?.apply {
            isDisplayed()
            matchWithText(mAppTitle)
        }

        // Slider
        withId(R.id.tabSlider)?.isDisplayed()
        withId(R.id.vpSlider)?.apply {
            isDisplayed()
            check(ViewPagerItemCountAssertion(unexpectedCount = 0))
        }

        // Tab menus
        withId(R.id.tabMenu)?.isDisplayed()
        withId(R.id.vpMenu)?.apply {
            isDisplayed()
            check(ViewPagerItemCountAssertion(unexpectedCount = 0))
        }
    }


}