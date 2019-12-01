package me.hafizdwp.jetpack_submission_final.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import me.hafizdwp.jetpack_submission_final.base.BaseViewModel
import me.hafizdwp.jetpack_submission_final.data.source.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.MyRepository
import me.hafizdwp.jetpack_submission_final.data.source.remote.ApiCallback
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState

/**
 * @author hafizdwp
 * 01/12/2019
 **/
class MainViewModel(val app: Application,
                    val mRepository: MyRepository) : BaseViewModel() {

    private val _requestState = MutableLiveData<MyRequestState>()
    val requestState: LiveData<MyRequestState> = Transformations.map(_requestState) { it }

    private val _sliderData = MutableLiveData<List<Movreak>>()
    val sliderData: LiveData<List<Movreak>> = Transformations.map(_sliderData) { it }

    fun getListSlider() = launch {

        _requestState.loading()
        mRepository.getTopRatedMovies(object : ApiCallback<List<Movreak>> {
            override fun onSuccess(data: List<Movreak>) {
                _requestState.success()
                _sliderData.value = data
            }

            override fun onFailed(e: Exception) {
                e.printStackTrace()
                _requestState.failed(e.toString())
            }
        })
    }
}