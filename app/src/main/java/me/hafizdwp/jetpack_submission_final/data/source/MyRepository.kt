package me.hafizdwp.jetpack_submission_final.data.source

import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.local.MyLocalDataSource
import me.hafizdwp.jetpack_submission_final.data.source.remote.ApiCallback
import me.hafizdwp.jetpack_submission_final.data.source.remote.MyRemoteDataSource

/**
 * @author hafizdwp
 * 20/11/2019
 **/
open class MyRepository : MyDataSource {

    val remoteDataSource = MyRemoteDataSource()
    val localDataSource = MyLocalDataSource()

    override suspend fun getTopRatedMovies(callback: ApiCallback<List<Movreak>>) {
        return remoteDataSource.getTopRatedMovies(callback)
    }

    override suspend fun getPopularMovies(callback: ApiCallback<List<Movreak>>) {
        return remoteDataSource.getPopularMovies(callback)
    }

    override suspend fun getPopularTvShows(callback: ApiCallback<List<Movreak>>) {
        return remoteDataSource.getPopularTvShows(callback)
    }

    override suspend fun saveFavorite(data: Movreak) {
        return localDataSource.saveFavorite(data)
    }

    override suspend fun deleteFavorite(data: Movreak) {
        return localDataSource.deleteFavorite(data)
    }

    override suspend fun getFavoriteById(id: Int, callback: ApiCallback<Movreak?>) {
        return localDataSource.getFavoriteById(id, callback)
    }

    override suspend fun getListFavoriteByType(typeInString: String, callback: ApiCallback<List<Movreak?>>) {
        return localDataSource.getListFavoriteByType(typeInString, callback)
    }

    //    override fun getPopularMovies(): LiveData<List<MovieResponse>> {
//        val liveData = MutableLiveData<List<MovieResponse>>()
//        remoteDataSource.getPopularMovies(object : ApiCallback<List<MovieResponse>> {
//            override fun onSuccess(data: List<MovieResponse>) {
//                liveData.postValue(data)
//            }
//
//            override fun onFailed(e: Exception) {
//                log(e.toString())
//                liveData.postValue(null)
//            }
//        })
//
//        return liveData
//    }
//
//    override fun getPopularTvShows(): LiveData<List<TvShowResponse>> {
//        val liveData = MutableLiveData<List<TvShowResponse>>()
//        remoteDataSource.getPopularTvShows(object : ApiCallback<List<TvShowResponse>> {
//            override fun onSuccess(data: List<TvShowResponse>) {
//                liveData.postValue(data)
//            }
//
//            override fun onFailed(e: Exception) {
//                log(e.toString())
//                liveData.postValue(null)
//            }
//        })
//
//        return liveData
//    }

    companion object {

        private var INSTANCE: MyRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.
         */
        @JvmStatic
        fun getInstance() =
                INSTANCE ?: synchronized(MyRepository::class.java) {
                    INSTANCE ?: MyRepository()
                            .also { INSTANCE = it }
                }
    }
}