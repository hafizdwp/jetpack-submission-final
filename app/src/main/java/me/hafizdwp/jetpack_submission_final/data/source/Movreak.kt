package me.hafizdwp.jetpack_submission_final.data.source

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.MovieResponse

/**
 * @author hafizdwp
 * 01/12/2019
 **/

@Parcelize
data class Movreak(
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
        fun toListFrom(list: List<MovieResponse>?): List<Movreak> {
            val array = arrayListOf<Movreak>()
            list?.forEach {
                array.add(Movreak(
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
    }
}