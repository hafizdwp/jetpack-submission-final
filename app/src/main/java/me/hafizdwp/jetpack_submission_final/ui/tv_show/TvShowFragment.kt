package me.hafizdwp.jetpack_submission_final.ui.tv_show

import android.app.ActivityOptions
import android.util.Pair
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.tv_show_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment
import me.hafizdwp.jetpack_submission_final.data.Const
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.ui.ContentActionListener
import me.hafizdwp.jetpack_submission_final.ui.ContentAdapter
import me.hafizdwp.jetpack_submission_final.ui.MainViewModel
import me.hafizdwp.jetpack_submission_final.ui.detail.DetailActivity
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState
import me.hafizdwp.jetpack_submission_final.utils.obtainViewModel

class TvShowFragment : BaseFragment(), ContentActionListener {

    companion object {
        fun newInstance() = TvShowFragment()
    }

    override val layoutRes: Int
        get() = R.layout.tv_show_fragment
    override val mLifecycleOwner: LifecycleOwner
        get() = this

    val mViewModel by lazy { obtainViewModel<MainViewModel>() }
    var mAdapter: ContentAdapter? = null
    val mListTvShows = arrayListOf<Movreak>()


    override fun onExtractArguments() {
    }

    override fun onReady() {

        setupObserver()
        setupRecycler()

        GlobalScope.launch { mViewModel.getPopularTvShows() }
    }

    fun setupObserver() = mViewModel.apply {
        tvState.observe {
            when (it) {
                is MyRequestState.Loading -> {
                    tvProgress.start()
                }
                is MyRequestState.Success -> {
                    tvProgress.stopAndGone()
                }
                is MyRequestState.Failed -> {
                    tvProgress.stopAndError(it.errorMsg ?: "")
                    tvProgress.setRetryClickListener {
                        GlobalScope.launch {
                            getPopularMovies()
                            getPopularTvShows()
                        }
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
        mAdapter = ContentAdapter(
                items = mListTvShows,
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
