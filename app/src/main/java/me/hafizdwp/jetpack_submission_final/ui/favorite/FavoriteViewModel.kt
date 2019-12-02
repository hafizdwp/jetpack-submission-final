package me.hafizdwp.jetpack_submission_final.ui.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import me.hafizdwp.jetpack_submission_final.base.BaseViewModel
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.MyRepository
import me.hafizdwp.jetpack_submission_final.data.source.remote.ApiCallback
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState
import me.hafizdwp.jetpack_submission_final.utils.livedata.SingleLiveEvent

/**
 * @author hafizdwp
 * 02/12/2019
 **/
class FavoriteViewModel(val app: Application,
                        val mRepository: MyRepository) : BaseViewModel() {

    val movieState = SingleLiveEvent<MyRequestState>()
    val tvState = SingleLiveEvent<MyRequestState>()

    val listMovies = MutableLiveData<List<Movreak?>>()
    val listTvShows = MutableLiveData<List<Movreak?>>()


    fun getListFavoritedMovies() = launch {
        movieState.loading()
        mRepository.getListFavoriteByType(Movreak.Type.MOVIE.name, object : ApiCallback<List<Movreak?>> {
            override fun onSuccess(data: List<Movreak?>) {
                movieState.success()

                if (data.isNullOrEmpty())
                    listMovies.value = null
                else
                    listMovies.value = data
            }

            override fun onFailed(e: Exception) {
                movieState.failed(e.toString())
            }
        })
    }

    fun getListFavoritedTvShow() = launch {
        tvState.loading()
        mRepository.getListFavoriteByType(Movreak.Type.TV_SHOW.name, object : ApiCallback<List<Movreak?>> {
            override fun onSuccess(data: List<Movreak?>) {
                tvState.success()

                if (data.isNullOrEmpty())
                    listTvShows.value = null
                else
                    listTvShows.value = data
            }

            override fun onFailed(e: Exception) {
                tvState.failed(e.toString())
            }
        })
    }
}