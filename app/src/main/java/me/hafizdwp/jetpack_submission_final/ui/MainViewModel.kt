package me.hafizdwp.jetpack_submission_final.ui

import android.app.Application
import me.hafizdwp.jetpack_submission_final.base.BaseViewModel
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.MyRepository
import me.hafizdwp.jetpack_submission_final.data.source.remote.ApiCallback
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState
import me.hafizdwp.jetpack_submission_final.utils.livedata.SingleLiveEvent

/**
 * @author hafizdwp
 * 01/12/2019
 **/
class MainViewModel(val app: Application,
                    val mRepository: MyRepository) : BaseViewModel() {

    val sliderState = SingleLiveEvent<MyRequestState>()
    val sliderData = SingleLiveEvent<List<Movreak>>()
    val movieState = SingleLiveEvent<MyRequestState>()
    val movieData = SingleLiveEvent<List<Movreak>>()
    val tvState = SingleLiveEvent<MyRequestState>()
    val tvData = SingleLiveEvent<List<Movreak>>()


    suspend fun getListSlider() {

        sliderState.loading()
        mRepository.getTopRatedMovies(object : ApiCallback<List<Movreak>> {
            override fun onSuccess(data: List<Movreak>) {
                sliderState.success()
//                sliderData.postValue(data)
                sliderData.value = (data)
            }

            override fun onFailed(e: Exception) {
                e.printStackTrace()
                sliderState.failed(e.toString())
            }
        })
    }

    suspend fun getPopularMovies() {

        movieState.loading()
        mRepository.getPopularMovies(object : ApiCallback<List<Movreak>> {
            override fun onSuccess(data: List<Movreak>) {
                movieState.success()
//                movieData.postValue(data)
                movieData.value = (data)
            }

            override fun onFailed(e: Exception) {
                e.printStackTrace()
                movieState.failed(e.toString())
            }
        })
    }

    suspend fun getPopularTvShows() {

        tvState.loading()
        mRepository.getPopularTvShows(object : ApiCallback<List<Movreak>> {
            override fun onSuccess(data: List<Movreak>) {
                tvState.success()
//                tvData.postValue(data)
                tvData.value = (data)
            }

            override fun onFailed(e: Exception) {
                e.printStackTrace()
                tvState.failed(e.toString())
            }
        })
    }
}