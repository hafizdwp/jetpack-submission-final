package me.hafizdwp.jetpack_submission_final.ui.favorite

import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.utils.ViewPagerItemCountAssertion
import me.hafizdwp.jetpack_submission_final.utils.isDisplayed
import me.hafizdwp.jetpack_submission_final.utils.matchWithText
import me.hafizdwp.jetpack_submission_final.utils.test.EspressoIdlingResource
import me.hafizdwp.jetpack_submission_final.utils.test.SingleFragmentActivity
import me.hafizdwp.jetpack_submission_final.utils.withId
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author hafizdwp
 * 02/12/2019
 **/
class FavoriteFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)

    private val favoriteFragment = FavoriteFragment()
    val mToolbarTitle = "Favorite"

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
        activityRule.activity.setFragment(favoriteFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun checkViews() {

        // Toolbar
        withId(R.id.toolbar)?.isDisplayed()
        withId(R.id.textToolbarTitle)?.apply {
            isDisplayed()
            matchWithText(mToolbarTitle)
        }

        // Menus
        withId(R.id.tabLayout)?.isDisplayed()
        withId(R.id.viewPager)?.apply {
            isDisplayed()
            check(ViewPagerItemCountAssertion(unexpectedCount = 0))
        }


    }
}