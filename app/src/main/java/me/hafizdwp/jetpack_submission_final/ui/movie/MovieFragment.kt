package me.hafizdwp.jetpack_submission_final.ui.movie

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.movie_fragment.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment
import me.hafizdwp.jetpack_submission_final.data.source.Movreak
import me.hafizdwp.jetpack_submission_final.ui.MainViewModel
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState
import me.hafizdwp.jetpack_submission_final.utils.obtainViewModel

class MovieFragment : BaseFragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    override val layoutRes: Int
        get() = R.layout.movie_fragment
    override val mLifecycleOwner: LifecycleOwner
        get() = this

    val mViewModel by lazy { obtainViewModel<MainViewModel>() }
    var mAdapter: MovieAdapter? = null
    val mListMovies = arrayListOf<Movreak>()


    override fun onExtractArguments() {

    }

    override fun onReady() {

        setupObserver()
        setupRecycler()

        mViewModel.getPopularMovies()
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
                        getPopularMovies()
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
        mAdapter = MovieAdapter(mListMovies)
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }
}
