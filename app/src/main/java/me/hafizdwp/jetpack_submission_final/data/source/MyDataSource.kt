package me.hafizdwp.jetpack_submission_final.data.source

import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.remote.ApiCallback

/**
 * @author hafizdwp
 * 20/11/2019
 **/
interface MyDataSource {

    ///
    ///
    /// REMOTE
    ///
    ///

    suspend fun getTopRatedMovies(callback: ApiCallback<List<Movreak>>)

    suspend fun getPopularMovies(callback: ApiCallback<List<Movreak>>)

    suspend fun getPopularTvShows(callback: ApiCallback<List<Movreak>>)
    ///
    ///
    /// LOCAL
    ///
    ///
    suspend fun saveFavorite(data: Movreak)

    suspend fun deleteFavorite(data: Movreak)

    suspend fun getFavoriteById(id: Int,
                                callback: ApiCallback<Movreak?>)

    suspend fun getListFavoriteByType(typeInString: String,
                                      callback: ApiCallback<List<Movreak?>>)
}