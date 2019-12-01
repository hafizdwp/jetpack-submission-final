package me.hafizdwp.jetpack_submission_final.data.source.remote.api

import me.hafizdwp.jetpack_submission_final.BuildConfig
import me.hafizdwp.jetpack_submission_final.base.BaseResponse
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.GenreResponse
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.TvShowDetailResponse
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author hafizdwp
 * 20/11/2019
 **/
interface TvShowApi {

    @GET("discover/tv?sort_by=popularity.desc")
    suspend fun getPopularTvShows(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): BaseResponse<List<TvShowResponse>>

    @GET("genre/tv/list")
    suspend fun getTvShowsGenre(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): BaseResponse<List<GenreResponse>>

    @GET("tv/on_the_air")
    suspend fun getOnAirTvShows(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): BaseResponse<List<TvShowResponse>>

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(
            @Path("tv_id") tvShowId: Int,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): TvShowDetailResponse
}