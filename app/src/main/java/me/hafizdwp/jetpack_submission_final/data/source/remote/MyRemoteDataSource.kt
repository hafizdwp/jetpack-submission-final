package me.hafizdwp.jetpack_submission_final.data.source.remote

import me.hafizdwp.jetpack_submission_final.data.ApiServiceFactory
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.remote.api.MovieApi
import me.hafizdwp.jetpack_submission_final.data.source.remote.api.TvShowApi
import me.hafizdwp.jetpack_submission_final.utils.asyncAwait
import me.hafizdwp.jetpack_submission_final.utils.test.EspressoIdlingResource

/**
 * @author hafizdwp
 * 20/11/2019
 **/
class MyRemoteDataSource {

    val movieApi by lazy { ApiServiceFactory.builder<MovieApi>() }
    val tvShowApi by lazy { ApiServiceFactory.builder<TvShowApi>() }

    suspend fun getTopRatedMovies(callback: ApiCallback<List<Movreak>>) {
        EspressoIdlingResource.increment()
        try {
            val response = asyncAwait { movieApi.getTopRatedMovies() }
            callback.onSuccess(Movreak.toListFromMovie(response.results))
        } catch (e: Exception) {
            callback.onFailed(e)
        } finally {
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getPopularMovies(callback: ApiCallback<List<Movreak>>) {
        EspressoIdlingResource.increment()
        try {
            val response = asyncAwait { movieApi.getPopularMovies() }
            callback.onSuccess(Movreak.toListFromMovie(response.results))
        } catch (e: Exception) {
            callback.onFailed(e)
        } finally {
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getPopularTvShows(callback: ApiCallback<List<Movreak>>) {
        EspressoIdlingResource.increment()
        try {
            val response = asyncAwait { tvShowApi.getPopularTvShows() }
            callback.onSuccess(Movreak.toListFromTv(response.results))
        } catch (e: Exception) {
            callback.onFailed(e)
        } finally {
            EspressoIdlingResource.decrement()
        }
    }

//    fun getPopularMovies(callback: ApiCallback<List<MovieResponse>>) {
////        EspressoIdlingResource.increment()
//
//        launchIO {
//            try {
//                val response = asyncAwait { movieApi.getPopularMovies() }
//                onMain {
//                    callback.onSuccess(response.results ?: emptyList())
////                    EspressoIdlingResource.decrement()
//                }
//            } catch (e: Exception) {
//                callback.onFailed(e)
//            }
//        }
//    }
//
//    fun getPopularTvShows(callback: ApiCallback<List<TvShowResponse>>) {
////        EspressoIdlingResource.increment()
//
//        launchIO {
//            try {
//                val response = asyncAwait { tvShowApi.getPopularTvShows() }
//                onMain {
//                    callback.onSuccess(response.results ?: emptyList())
////                    EspressoIdlingResource.decrement()
//                }
//            } catch (e: Exception) {
//                callback.onFailed(e)
//            }
//        }
//    }
}