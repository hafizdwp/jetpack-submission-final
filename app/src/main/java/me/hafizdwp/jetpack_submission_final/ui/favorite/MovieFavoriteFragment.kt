package me.hafizdwp.jetpack_submission_final.ui.favorite

import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.movie_favorite_fragment.*
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
class MovieFavoriteFragment : BaseFragment(), ContentActionListener {

    companion object {
        fun newInstance() = MovieFavoriteFragment().withArgs { }
    }

    override val layoutRes: Int
        get() = R.layout.movie_favorite_fragment
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

        callMoviesFavorite()
    }

    fun setupObserver() = mViewModel.apply {
        movieState.observe {
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
                        callMoviesFavorite()
                    }
                }
            }
        }

        listPagedMovies?.observe {
            if (!it?.toList().isNullOrEmpty()) {
                mAdapter?.submitList(it)
            } else {
                myProgressView.start()
                myProgressView.stopAndEmptyFavorite()
            }
        }

        shouldRefreshData.observe {
            callMoviesFavorite()
        }
    }

    fun callMoviesFavorite() = mViewModel.apply {

        movieState.loading()

        listPagedMovies?.observe {
            if (!it?.toList().isNullOrEmpty()) {
                movieState.success()
                mAdapter?.submitList(it)
            } else {
                myProgressView.start()
                myProgressView.stopAndEmptyFavorite()
            }
        }
    }

    fun setupRecycler() {
        mAdapter = ContentPagedAdapter(
                actionListener = this@MovieFavoriteFragment
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
        DetailActivity.startActivity(
                context = requireContext(),
                data = data
        )
    }
}