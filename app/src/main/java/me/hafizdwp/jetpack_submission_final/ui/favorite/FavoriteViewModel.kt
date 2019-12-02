package me.hafizdwp.jetpack_submission_final.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import me.hafizdwp.jetpack_submission_final.base.BaseViewModel
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.MyRepository
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState

/**
 * @author hafizdwp
 * 02/12/2019
 **/
class FavoriteViewModel(val app: Application,
                        val mRepository: MyRepository) : BaseViewModel() {

    companion object {
        const val PAGINATION_SIZE = 5
    }

    val movieState = MutableLiveData<MyRequestState>()
    val tvState = MutableLiveData<MyRequestState>()
    val shouldRefreshData = MutableLiveData<Void>()

    val listPagedMovies: LiveData<PagedList<Movreak>>? =
            mRepository.getPagedListFavoriteByType(Movreak.Type.MOVIE.name)?.toLiveData(PAGINATION_SIZE)
    val listPagedTvShows: LiveData<PagedList<Movreak>>? =
            mRepository.getPagedListFavoriteByType(Movreak.Type.TV_SHOW.name)?.toLiveData(PAGINATION_SIZE)
}