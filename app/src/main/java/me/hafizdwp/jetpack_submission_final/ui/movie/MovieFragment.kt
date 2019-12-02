package me.hafizdwp.jetpack_submission_final.ui.movie

import android.app.ActivityOptions
import android.util.Pair
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.movie_fragment.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment
import me.hafizdwp.jetpack_submission_final.data.Const
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.ui.ContentActionListener
import me.hafizdwp.jetpack_submission_final.ui.ContentAdapter
import me.hafizdwp.jetpack_submission_final.ui.MainViewModel
import me.hafizdwp.jetpack_submission_final.ui.detail.DetailActivity
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState
import me.hafizdwp.jetpack_submission_final.utils.launchMain
import me.hafizdwp.jetpack_submission_final.utils.obtainViewModel

class MovieFragment : BaseFragment(), ContentActionListener {

    companion object {
        fun newInstance() = MovieFragment()
    }

    override val layoutRes: Int
        get() = R.layout.movie_fragment
    override val mLifecycleOwner: LifecycleOwner
        get() = this

    val mViewModel by lazy { obtainViewModel<MainViewModel>() }
    var mAdapter: ContentAdapter? = null
    val mListMovies = arrayListOf<Movreak>()


    override fun onExtractArguments() {

    }

    override fun onReady() {
        setupObserver()
        setupRecycler()

        launchMain { mViewModel.getPopularMovies() }
    }

    fun setupObserver() = mViewModel.apply {
        movieState.observe {
            when (it) {
                is MyRequestState.Loading -> {
                    movieProgress.start()
                }
                is MyRequestState.Success -> {
                    movieProgress.stopAndGone()
                }
                is MyRequestState.Failed -> {
                    movieProgress.stopAndError(it.errorMsg ?: "")
                    movieProgress.setRetryClickListener {
                        launchMain {
                            getPopularMovies()
                            getPopularTvShows()
                        }
                    }
                }
            }
        }

        movieData.observe {
            it?.let {
                mListMovies.clear()
                mListMovies.addAll(it)
                mAdapter?.notifyDataSetChanged()
            }
        }
    }

    fun setupRecycler() {
        mAdapter = ContentAdapter(
                items = mListMovies,
                actionListener = this)

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

        DetailActivity.startActivity(
                context = requireContext(),
                options = options,
                data = data
        )
    }
}
