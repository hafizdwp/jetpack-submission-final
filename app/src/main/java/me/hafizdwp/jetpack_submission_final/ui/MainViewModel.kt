package me.hafizdwp.jetpack_submission_final.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import me.hafizdwp.jetpack_submission_final.base.BaseViewModel
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.MyRepository
import me.hafizdwp.jetpack_submission_final.data.source.remote.ApiCallback
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState

/**
 * @author hafizdwp
 * 01/12/2019
 **/
class MainViewModel(val app: Application,
                    val mRepository: MyRepository) : BaseViewModel() {

    private val _sliderState = MutableLiveData<MyRequestState>()
    val sliderState: LiveData<MyRequestState> = Transformations.map(_sliderState) { it }

    private val _sliderData = MutableLiveData<List<Movreak>>()
    val sliderData: LiveData<List<Movreak>> = Transformations.map(_sliderData) { it }

    private val _movieState = MutableLiveData<MyRequestState>()
    val movieState: LiveData<MyRequestState> = Transformations.map(_movieState) { it }

    private val _movieData = MutableLiveData<List<Movreak>>()
    val movieData: LiveData<List<Movreak>> = Transformations.map(_movieData) { it }

    private val _tvState = MutableLiveData<MyRequestState>()
    val tvState: LiveData<MyRequestState> = Transformations.map(_tvState) { it }

    private val _tvData = MutableLiveData<List<Movreak>>()
    val tvData: LiveData<List<Movreak>> = Transformations.map(_tvData) { it }


    fun getListSlider() = launch {

        _sliderState.loading()
        mRepository.getTopRatedMovies(object : ApiCallback<List<Movreak>> {
            override fun onSuccess(data: List<Movreak>) {
                _sliderState.success()
                _sliderData.value = data
            }

            override fun onFailed(e: Exception) {
                e.printStackTrace()
                _sliderState.failed(e.toString())
            }
        })
    }

    fun getPopularMovies() = launch {

        _movieState.loading()
        mRepository.getPopularMovies(object : ApiCallback<List<Movreak>> {
            override fun onSuccess(data: List<Movreak>) {
                _movieState.success()
                _movieData.value = data
            }

            override fun onFailed(e: Exception) {
                e.printStackTrace()
                _movieState.failed(e.toString())
            }
        })
    }

    fun getPopularTvShows() = launch {

        _tvState.loading()
        mRepository.getPopularTvShows(object : ApiCallback<List<Movreak>> {
            override fun onSuccess(data: List<Movreak>) {
                _tvState.success()
                _tvData.value = data
            }

            override fun onFailed(e: Exception) {
                e.printStackTrace()
                _tvState.failed(e.toString())
            }
        })
    }
}