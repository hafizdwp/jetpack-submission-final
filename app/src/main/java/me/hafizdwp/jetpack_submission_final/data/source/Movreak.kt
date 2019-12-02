package me.hafizdwp.jetpack_submission_final.data.source

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.MovieResponse
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.TvShowResponse

/**
 * @author hafizdwp
 * 01/12/2019
 **/

@Parcelize
data class Movreak(
        val id: Int = 0,
        val title: String? = "",
        val overview: String? = "",
        val posterPath: String? = "",
        val backdropPath: String? = "",
        val rating: Double? = 0.0,
        val releaseDate: String? = "",
        val type: Type
) : Parcelable {

    enum class Type {
        MOVIE, TV_SHOW
    }

    companion object {
        fun toListFromMovie(list: List<MovieResponse>?): List<Movreak> {
            val array = arrayListOf<Movreak>()
            list?.forEach {
                array.add(Movreak(
                        id = it.id ?: 0,
                        title = it.title,
                        overview = it.overview,
                        posterPath = it.poster_path,
                        backdropPath = it.backdrop_path,
                        rating = it.vote_average,
                        releaseDate = it.release_date,
                        type = Type.MOVIE
                ))
            }
            return array
        }

        fun toListFromTv(list: List<TvShowResponse>?): List<Movreak> {
            val array = arrayListOf<Movreak>()
            list?.forEach {
                array.add(Movreak(
                        id = it.id ?: 0,
                        title = it.name,
                        overview = it.overview,
                        posterPath = it.poster_path,
                        backdropPath = it.backdrop_path,
                        rating = it.vote_average,
                        releaseDate = it.first_air_date,
                        type = Type.TV_SHOW
                ))
            }
            return array
        }
    }
}