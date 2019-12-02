package me.hafizdwp.jetpack_submission_final.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.hafizdwp.jetpack_submission_final.MyApp
import me.hafizdwp.jetpack_submission_final.data.source.MyRepository
import me.hafizdwp.jetpack_submission_final.utils.observeOnce
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * @author hafizdwp
 * 02/12/2019
 */
class MainViewModelTest {

    // A JUnit Test Rule that swaps the background executor used by
    // the Architecture Components with a different one which executes each task synchronously.
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: MyRepository

    @Before
    fun setup() {
        repository = MyRepository()
        viewModel = MainViewModel(Mockito.mock(MyApp::class.java), repository)
    }

    @Test
    fun getListSlider() {
        runBlocking {
            launch {
                viewModel.getListSlider()
            }

            viewModel.sliderData.observeOnce {
                assertNotNull(it)
                assertNotEquals(0, it.size)
            }
        }
    }

    @Test
    fun getPopularMovies() {
        runBlocking {
            launch {
                viewModel.getPopularMovies()
            }
            viewModel.movieData.observeOnce {
                assertNotNull(it)
                assertNotEquals(0, it.size)
            }
        }
    }

    @Test
    fun getPopularTvShows() {
        runBlocking {
            launch {
                viewModel.getPopularTvShows()
            }
            viewModel.tvData.observeOnce {
                assertNotNull(it)
                assertNotEquals(0, it.size)
            }
        }
    }
}