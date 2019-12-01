package me.hafizdwp.jetpack_submission_final.ui.favorite

import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.movie_favorite_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment
import me.hafizdwp.jetpack_submission_final.utils.withArgs

/**
 * @author hafizdwp
 * 02/12/2019
 **/
class MovieFavoriteFragment : BaseFragment() {

    companion object {
        fun newInstance() = MovieFavoriteFragment().withArgs { }
    }

    override val layoutRes: Int
        get() = R.layout.movie_favorite_fragment
    override val mLifecycleOwner: LifecycleOwner
        get() = this


    override fun onExtractArguments() {
    }

    override fun onReady() {
        GlobalScope.launch(Dispatchers.Main) {
            myProgressView.start()
            delay(1000L)
            myProgressView.stopAndEmptyFavorite()
        }
    }
}