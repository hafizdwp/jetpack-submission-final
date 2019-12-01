package me.hafizdwp.jetpack_submission_final.data.source.remote.api

import me.hafizdwp.jetpack_submission_final.BuildConfig
import me.hafizdwp.jetpack_submission_final.base.BaseResponse
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.GenreResponse
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.MovieDetailResponse
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author hafizdwp
 * 20/11/2019
 **/
interface MovieApi {

//    @GET("discover/movie?sort_by=popularity.desc")
//    fun getPopularMovies(
//            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
//            @Query("language") language: String = prefs[Constant.PREF_LANGUAGE_API_QUERY] ?: "en-US"
//    ): Observable<BaseResponse<List<MovieResponse>>>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): BaseResponse<List<MovieResponse>>

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): BaseResponse<List<MovieResponse>>

    @GET("genre/movie/list")
    suspend fun getMoviesGenre(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): BaseResponse<List<GenreResponse>>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): BaseResponse<List<MovieResponse>>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieDetailResponse
}