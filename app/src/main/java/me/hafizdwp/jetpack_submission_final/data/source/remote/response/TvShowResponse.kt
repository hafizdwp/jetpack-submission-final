package me.hafizdwp.jetpack_submission_final.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author hafizdwp
 * 20/11/2019
 **/
@Parcelize
data class TvShowResponse(
        var original_name: String? = null,
        var genre_ids: List<Int?>? = null,
        var name: String? = null,
        var popularity: Double? = null,
        var origin_country: List<String?>? = null,
        var vote_count: Int? = null,
        var first_air_date: String? = null,
        var backdrop_path: String? = null,
        var original_language: String? = null,
        var id: Int? = null,
        var vote_average: Double? = null,
        var overview: String? = null,
        var poster_path: String? = null,

        // self-made variable
        var listGenre: List<String>? = null
) : Parcelable