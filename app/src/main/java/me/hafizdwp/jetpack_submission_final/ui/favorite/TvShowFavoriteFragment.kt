package me.hafizdwp.jetpack_submission_final.ui.favorite

import android.app.ActivityOptions
import android.util.Pair
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.tv_show_favorite_fragment.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment
import me.hafizdwp.jetpack_submission_final.data.Const
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.ui.ContentActionListener
import me.hafizdwp.jetpack_submission_final.ui.detail.DetailActivity
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState
import me.hafizdwp.jetpack_submission_final.utils.launchMain
import me.hafizdwp.jetpack_submission_final.utils.obtainViewModel
import me.hafizdwp.jetpack_submission_final.utils.withArgs

/**
 * @author hafizdwp
 * 02/12/2019
 **/
class TvShowFavoriteFragment : BaseFragment(), ContentActionListener {

    companion object {
        fun newInstance() = TvShowFavoriteFragment().withArgs { }
    }

    override val layoutRes: Int
        get() = R.layout.tv_show_favorite_fragment
    override val mLifecycleOwner: LifecycleOwner
        get() = this

    val mViewModel by lazy { obtainViewModel<FavoriteViewModel>() }
    val mListTvShows = arrayListOf<Movreak>()
    var mAdapter: TvShowFavoriteAdapter? = null


    override fun onExtractArguments() {
    }

    override fun onReady() {
        myProgressView.useDarkerBackground()
        setupObserver()
        setupRecycler()

        launchMain { mViewModel.getListFavoritedTvShow() }
    }

    fun setupObserver() = mViewModel.apply {
        tvState.observe {
            when (it) {
                is MyRequestState.Loading -> {
                    myProgressView.start()
                }
                is MyRequestState.Success -> {
                    myProgressView.stopAndGone()
                }
                is MyRequestState.Failed -> {
                    myProgressView.stopAndError(it.errorMsg ?: "")
                    myProgressView.setRetryClickListener {
                        launchMain { mViewModel.getListFavoritedTvShow() }
                    }
                }
            }
        }

        listTvShows.observe {
            if (!it.isNullOrEmpty()) {
                mListTvShows.clear()

                it.forEach { dataNullable ->
                    dataNullable?.let { data ->
                        mListTvShows.add(data)
                    }
                }

                mAdapter?.notifyDataSetChanged()
            } else {
                myProgressView.start()
                myProgressView.stopAndEmptyFavorite()
            }
        }

        shouldRefreshData.observe {
            launchMain { mViewModel.getListFavoritedTvShow() }
        }
    }

    fun setupRecycler() {
        mAdapter = TvShowFavoriteAdapter(
                items = mListTvShows,
                actionListener = this@TvShowFavoriteFragment
        )

        recyclerView.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    /**
     * ContentActionListener implementation
     * ---------------------------------------------------------------------------------------------
     * */
    override fun onItemClick(data: Movreak, imgView: ImageView) {
        val options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                Pair.create(imgView, Const.SHARED_ELEMENT_POSTER)
        )

        DetailActivity.startActivityForResults(
                context = requireContext(),
                fragment = (parentFragment as? FavoriteFragment) ?: this,
                options = options,
                data = data
        )
    }
}