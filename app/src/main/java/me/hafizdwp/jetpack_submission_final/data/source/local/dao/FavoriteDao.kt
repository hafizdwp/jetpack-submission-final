package me.hafizdwp.jetpack_submission_final.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.hafizdwp.jetpack_submission_final.data.model.Movreak

/**
 * @author hafizdwp
 * 02/12/2019
 **/

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM ${Movreak.TABLE_NAME} WHERE ${Movreak.COLUMN_ID} IN(:id) LIMIT 1")
    suspend fun getFavorite(id: Int): Movreak?

    @Insert
    suspend fun insertFavorite(movreak: Movreak?)

    @Query("DELETE FROM ${Movreak.TABLE_NAME} WHERE ${Movreak.COLUMN_ID} IN(:id)")
    suspend fun deleteFavorite(id: Int?)
}