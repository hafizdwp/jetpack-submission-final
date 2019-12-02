package me.hafizdwp.jetpack_submission_final.ui.detail

import android.content.Intent
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.utils.isDisplayed
import me.hafizdwp.jetpack_submission_final.utils.matchWithText
import me.hafizdwp.jetpack_submission_final.utils.withId
import org.junit.Rule
import org.junit.Test

/**
 * @author hafizdwp
 * 02/12/2019
 **/
class DetailActivityTest {


    val fakeData = Movreak(
            id = 82856,
            title = "The Mandalorian",
            overview = "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
            posterPath = "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
            backdropPath = "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
            releaseDate = "2019-11-12",
            rating = 7.8,
            type = Movreak.Type.TV_SHOW
    )

    @get:Rule
    val activityRule = object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailActivity::class.java)
            result.putExtra(DetailActivity.EXTRA_MOVREAK_DATA, fakeData)
            return result
        }
    }

    @Test
    fun loadDetail() {

        // Toolbar
        withId(R.id.toolbar)?.isDisplayed()
        withId(R.id.textToolbarTitle)?.apply {
            isDisplayed()
            matchWithText(fakeData.title ?: "")
        }

        // Image Backdrop & Poster
        withId(R.id.imgPhoto)?.isDisplayed()
        withId(R.id.imgPoster)?.isDisplayed()

        // Contents
        withId(R.id.imgFavorite)?.apply {
            isDisplayed()
            check(ViewAssertions.matches(ViewMatchers.isClickable()))
        }
        withId(R.id.textTitle)?.apply {
            isDisplayed()
            matchWithText(fakeData.title ?: "")
        }
        withId(R.id.ratingBar)?.isDisplayed()
        withId(R.id.textRating)?.apply {
            isDisplayed()
            matchWithText(fakeData.rating.toString())
        }
        withId(R.id.textDescLabel)?.isDisplayed()
        withId(R.id.textDesc)?.apply {
            isDisplayed()
            matchWithText(fakeData.overview ?: "")
        }

    }
}