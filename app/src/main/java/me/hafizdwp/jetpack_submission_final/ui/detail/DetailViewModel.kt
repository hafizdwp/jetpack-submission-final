package me.hafizdwp.jetpack_submission_final.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import me.hafizdwp.jetpack_submission_final.base.BaseViewModel
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.MyRepository
import me.hafizdwp.jetpack_submission_final.data.source.remote.ApiCallback

/**
 * @author hafizdwp
 * 02/12/2019
 **/
class DetailViewModel(val app: Application,
                      val mRepository: MyRepository) : BaseViewModel() {

    val favoriteStatus = MutableLiveData<Boolean>()


    fun setFavorite(data: Movreak?) = launch {
        data?.let {
            when (favoriteStatus.value) {
                true -> {
                    mRepository.deleteFavorite(data)
                    favoriteStatus.value = !(favoriteStatus.value ?: false)
                    globalToast.value = "'${data.title}' removed from favorite"
                }
                false -> {
                    mRepository.saveFavorite(data)
                    favoriteStatus.value = !(favoriteStatus.value ?: false)
                    globalToast.value = "'${data.title}' added to favorite"
                }
            }
        }
//
//        data?.let {
//            mRepository.saveFavorite(data)
//            favoriteStatus.value = !isFavorited
//        }
    }

    fun getFavoriteById(id: Int) = launch {
        mRepository.getFavoriteById(id, object : ApiCallback<Movreak?> {
            override fun onSuccess(data: Movreak?) {
                favoriteStatus.value = data != null
            }

            override fun onFailed(e: Exception) {
            }
        })
    }
}