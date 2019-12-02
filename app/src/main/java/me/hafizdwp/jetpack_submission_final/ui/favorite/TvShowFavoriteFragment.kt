package me.hafizdwp.jetpack_submission_final.ui.favorite

import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.tv_show_favorite_fragment.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.ui.ContentActionListener
import me.hafizdwp.jetpack_submission_final.ui.detail.DetailActivity
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState
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
    var mAdapter: ContentPagedAdapter? = null


    override fun onExtractArguments() {
    }

    override fun onReady() {
        myProgressView.useDarkerBackground()
        setupObserver()
        setupRecycler()

        callTvShowFavorite()
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
                        callTvShowFavorite()
                    }
                }
            }
        }

        shouldRefreshData.observe {
            callTvShowFavorite()
        }
    }

    fun callTvShowFavorite() = mViewModel.apply {

        tvState.loading()

        listPagedTvShows?.observe {
            if (!it?.toList().isNullOrEmpty()) {
                tvState.success()
                mAdapter?.submitList(it)
            } else {
                myProgressView.start()
                myProgressView.stopAndEmptyFavorite()
            }
        }
    }

    fun setupRecycler() {
        mAdapter = ContentPagedAdapter(
                actionListener = this@TvShowFavoriteFragment
        )

        recyclerTvFavorite.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    /**
     * ContentActionListener implementation
     * ---------------------------------------------------------------------------------------------
     * */
    override fun onItemClick(data: Movreak, imgView: ImageView) {
        DetailActivity.startActivity(
                context = requireContext(),
                data = data
        )
    }
}