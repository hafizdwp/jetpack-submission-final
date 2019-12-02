package me.hafizdwp.jetpack_submission_final.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.MovieResponse
import me.hafizdwp.jetpack_submission_final.data.source.remote.response.TvShowResponse

/**
 * @author hafizdwp
 * 01/12/2019
 **/

@Entity(tableName = Movreak.TABLE_NAME)
@Parcelize
data class Movreak(
        @PrimaryKey
        @ColumnInfo(name = COLUMN_ID)
        val id: Int = 0,

        @ColumnInfo(name = COLUMN_TITLE)
        val title: String? = "",

        @ColumnInfo(name = COLUMN_OVERVIEW)
        val overview: String? = "",

        @ColumnInfo(name = COLUMN_POSTER_PATH)
        val posterPath: String? = "",

        @ColumnInfo(name = COLUMN_BACKDROP_PATH)
        val backdropPath: String? = "",

        @ColumnInfo(name = COLUMN_RATING)
        val rating: Double? = 0.0,

        @ColumnInfo(name = COLUMN_RELEASE_DATE)
        val releaseDate: String? = "",

        @ColumnInfo(name = COLUMN_TYPE)
        val type: Type
) : Parcelable {

    enum class Type {
        MOVIE, TV_SHOW
    }

    companion object {
        const val TABLE_NAME = "favorite"
        const val COLUMN_ID = "_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_OVERVIEW = "overview"
        const val COLUMN_POSTER_PATH = "posterPath"
        const val COLUMN_BACKDROP_PATH = "backdropPath"
        const val COLUMN_RATING = "rating"
        const val COLUMN_RELEASE_DATE = "releaseDate"
        const val COLUMN_TYPE = "type"

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