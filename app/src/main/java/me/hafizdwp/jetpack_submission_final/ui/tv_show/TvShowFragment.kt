package me.hafizdwp.jetpack_submission_final.ui.tv_show

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.tv_show_fragment.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment
import me.hafizdwp.jetpack_submission_final.data.source.Movreak
import me.hafizdwp.jetpack_submission_final.ui.MainContentAdapter
import me.hafizdwp.jetpack_submission_final.ui.MainViewModel
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState
import me.hafizdwp.jetpack_submission_final.utils.obtainViewModel
import me.hafizdwp.jetpack_submission_final.utils.toJson
import me.hafizdwp.jetpack_submission_final.utils.toastSpammable

class TvShowFragment : BaseFragment(), MainContentActionListener {

    companion object {
        fun newInstance() = TvShowFragment()
    }

    override val layoutRes: Int
        get() = R.layout.tv_show_fragment
    override val mLifecycleOwner: LifecycleOwner
        get() = this

    val mViewModel by lazy { obtainViewModel<MainViewModel>() }
    var mAdapter: MainContentAdapter? = null
    val mListTvShows = arrayListOf<Movreak>()


    override fun onExtractArguments() {
    }

    override fun onReady() {

        setupObserver()
        setupRecycler()

        mViewModel.getPopularTvShows()
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
                        getPopularMovies()
                    }
                }
            }
        }

        tvData.observe {
            it?.let {
                mListTvShows.clear()
                mListTvShows.addAll(it)
                mAdapter?.notifyDataSetChanged()
            }
        }
    }

    fun setupRecycler() {
        mAdapter = MainContentAdapter(
                items = mListTvShows,
                actionListener = this)

        recyclerView.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    /**
     * MainContentActionListener implementation
     * ---------------------------------------------------------------------------------------------
     * */
    override fun onItemClick(data: Movreak) {
        toastSpammable(data.toJson())
    }
}
