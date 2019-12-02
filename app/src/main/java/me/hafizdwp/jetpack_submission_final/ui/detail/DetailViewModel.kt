package me.hafizdwp.jetpack_submission_final.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import me.hafizdwp.jetpack_submission_final.base.BaseViewModel
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.MyRepository

/**
 * @author hafizdwp
 * 02/12/2019
 **/
class DetailViewModel(val app: Application,
                      val mRepository: MyRepository) : BaseViewModel() {

    val favoriteStatus = MutableLiveData<Boolean>()


    fun saveFavorite(data: Movreak?,
                     isFavorited: Boolean) = launch {
        mRepository.saveFavorite(data!!)
        favoriteStatus.value = !isFavorited
    }
}