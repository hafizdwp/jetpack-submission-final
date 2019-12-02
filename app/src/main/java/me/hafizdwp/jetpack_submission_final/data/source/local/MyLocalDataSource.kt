package me.hafizdwp.jetpack_submission_final.data.source.local

import me.hafizdwp.jetpack_submission_final.MyApp
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.local.dao.FavoriteDao
import me.hafizdwp.jetpack_submission_final.data.source.remote.ApiCallback

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

    suspend fun deleteFavorite(data: Movreak) {
        try {
            favoriteDao?.deleteFavorite(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getFavoriteById(id: Int,
                                callback: ApiCallback<Movreak?>) {
        try {
            val data = favoriteDao?.getFavoriteById(id)
            callback.onSuccess(data)
        } catch (e: Exception) {
            callback.onFailed(e)
        }
    }

    suspend fun getListFavoriteByType(typeInString: String,
                                      callback: ApiCallback<List<Movreak?>>) {
        try {
            val data = favoriteDao?.getListFavoriteByType(typeInString)
            callback.onSuccess(data ?: emptyList())
        } catch (e: Exception) {
            callback.onFailed(e)
        }
    }
}