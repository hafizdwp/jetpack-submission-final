package me.hafizdwp.jetpack_submission_final.utils

import androidx.room.TypeConverter
import me.hafizdwp.jetpack_submission_final.data.model.Movreak

class RoomConverters {

    @TypeConverter
    fun fromTypeToString(type: Movreak.Type): String? {
        return type.name
    }

    @TypeConverter
    fun toTypeFromString(string: String): Movreak.Type {
        return when (string) {
            Movreak.Type.MOVIE.name -> Movreak.Type.MOVIE
            Movreak.Type.TV_SHOW.name -> Movreak.Type.TV_SHOW
            else -> throw Exception("error nih")
        }
    }
}