package me.hafizdwp.jetpack_submission_final.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author hafizdwp
 * 20/11/2019
 **/
@Parcelize
data class MovieResponse(
        var vote_count: Int? = null,
        var id: Int? = null,
        var video: Boolean? = null,
        var vote_average: Double? = null,
        var title: String? = null,
        var popularity: Double? = null,
        var poster_path: String? = null,
        var original_language: String? = null,
        var original_title: String? = null,
        var genre_ids: List<Int?>? = null,
        var backdrop_path: String? = null,
        var adult: Boolean? = null,
        var overview: String? = null,
        var release_date: String? = null,

        // self-made variable
        var listGenre: List<String>? = null
) : Parcelable