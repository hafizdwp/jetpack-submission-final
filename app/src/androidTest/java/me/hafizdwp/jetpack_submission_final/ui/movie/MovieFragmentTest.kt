package me.hafizdwp.jetpack_submission_final.ui.movie

import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.utils.RecyclerViewItemCountAssertion
import me.hafizdwp.jetpack_submission_final.utils.test.SingleFragmentActivity
import me.hafizdwp.jetpack_submission_final.utils.isDisplayed
import me.hafizdwp.jetpack_submission_final.utils.test.EspressoIdlingResource
import me.hafizdwp.jetpack_submission_final.utils.withId
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author hafizdwp
 * 02/12/2019
 */
class MovieFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)

    private val movieFragment = MovieFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
        activityRule.activity.setFragment(movieFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovie() {

        // RecyclerView
        withId(R.id.recyclerView)?.apply {
            isDisplayed()
            check(RecyclerViewItemCountAssertion(unexpectedCount = 0))
        }
    }
}