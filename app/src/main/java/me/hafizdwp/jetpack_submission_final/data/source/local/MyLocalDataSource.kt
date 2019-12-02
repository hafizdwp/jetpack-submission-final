package me.hafizdwp.jetpack_submission_final.data.source.local

import me.hafizdwp.jetpack_submission_final.MyApp
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.local.dao.FavoriteDao

/**
 * @author hafizdwp
 * 20/11/2019
 **/
class MyLocalDataSource {

    private var favoriteDao: FavoriteDao? = null

    init {
        AppDatabase.getInstance(MyApp.getContext())?.let {
            favoriteDao = it.favoriteDao()
        }
    }

    suspend fun saveFavorite(data: Movreak) {
        try {
            favoriteDao?.insertFavorite(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}