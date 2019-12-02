package me.hafizdwp.jetpack_submission_final.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import me.hafizdwp.jetpack_submission_final.MyApp
import me.hafizdwp.jetpack_submission_final.data.source.MyRepository
import me.hafizdwp.jetpack_submission_final.ui.favorite.FavoriteViewModel
import me.hafizdwp.jetpack_submission_final.utils.observeOnce
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * @author hafizdwp
 * 02/12/2019
 */
class FavoriteViewModelTest {

    // A JUnit Test Rule that swaps the background executor used by
    // the Architecture Components with a different one which executes each task synchronously.
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var repository: MyRepository

    @Before
    fun setup() {
        repository = MyRepository()
        viewModel = FavoriteViewModel(Mockito.mock(MyApp::class.java), repository)
    }

    @Test
    fun getListFavoritedMovies() {
        viewModel.listPagedMovies?.observeOnce {
            assertNotNull(it)
        }
    }

    @Test
    fun getListFavoritedTvShows() {
        viewModel.listPagedTvShows?.observeOnce {
            assertNotNull(it)
        }
    }
}