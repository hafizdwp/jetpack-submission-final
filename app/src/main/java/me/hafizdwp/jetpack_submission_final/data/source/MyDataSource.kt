package me.hafizdwp.jetpack_submission_final.data.source

import androidx.lifecycle.LiveData
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.MovieResponse
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.TvShowResponse

/**
 * @author hafizdwp
 * 20/11/2019
 **/
interface MyDataSource {

    ///
    ///
    /// REMOTE
    ///
    ///

    fun getPopularMovies(): LiveData<List<MovieResponse>> {
        throw Exception("remote only")
    }

    fun getPopularTvShows(): LiveData<List<TvShowResponse>> {
        throw Exception("remote only")
    }
    ///
    ///
    /// LOCAL
    ///
    ///
}