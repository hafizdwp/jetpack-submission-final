package me.hafizdwp.jetpack_submission_final.ui

import androidx.lifecycle.LifecycleOwner
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.main_fragment.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment
import me.hafizdwp.jetpack_submission_final.base.BasePagerAdapter
import me.hafizdwp.jetpack_submission_final.data.source.Movreak
import me.hafizdwp.jetpack_submission_final.ui.movie.MovieFragment
import me.hafizdwp.jetpack_submission_final.ui.tv_show.TvShowFragment
import me.hafizdwp.jetpack_submission_final.utils.MyRequestState
import me.hafizdwp.jetpack_submission_final.utils.gone
import me.hafizdwp.jetpack_submission_final.utils.obtainViewModel
import me.hafizdwp.jetpack_submission_final.utils.visible

/**
 * @author hafizdwp
 * 30/11/2019
 **/
class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override val layoutRes: Int
        get() = R.layout.main_fragment
    override val mLifecycleOwner: LifecycleOwner
        get() = this

    val mViewModel by lazy { obtainViewModel<MainViewModel>() }
    var mSliderAdapter: BasePagerAdapter? = null
    var mTabAdapter: MainMenuAdapter? = null
    val mListSlider = arrayListOf<Movreak>()


    override fun onExtractArguments() {
    }

    override fun onReady() {

        //setupSlider()
        setupMenuTabs()
        setupObserver()

        mViewModel.getListSlider()
    }

    fun setupObserver() = mViewModel.apply {
        sliderState.observe {
            when (it) {
                is MyRequestState.Loading -> {
                    myProgressView.start()
                    vpSlider.gone()
                    tabSlider.gone()
                }
                is MyRequestState.Success -> {
                    myProgressView.stopAndGone()
                    vpSlider.visible()
                    tabSlider.visible()
                }
                is MyRequestState.Failed -> {
                    myProgressView.stopAndError(it.errorMsg ?: "")
                    myProgressView.setRetryClickListener {
                        getListSlider()
                    }
                }
            }
        }

        sliderData.observe {
            it?.let {
                mListSlider.clear()
                mListSlider.addAll(it)
                setupSlider()
            }
        }
    }

    fun setupSlider() {
        mSliderAdapter = BasePagerAdapter(childFragmentManager)

        mListSlider.forEach {
            mSliderAdapter?.addFragment(
                    fragment = MainSliderFragment.newInstance(it)
            )
        }

        vpSlider.adapter = mSliderAdapter
        tabSlider.setupWithViewPager(vpSlider)
    }

    fun setupMenuTabs() {
        mTabAdapter = MainMenuAdapter(childFragmentManager)
        mTabAdapter?.addFragment(
                fragment = MovieFragment.newInstance(),
                title = getString(R.string.movie)
        )
        mTabAdapter?.addFragment(
                fragment = TvShowFragment.newInstance(),
                title = getString(R.string.tv_show)
        )

        vpMenu.adapter = mTabAdapter
        vpMenu.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                inflateCustomTabs()
                inflateCustomTabSelected(position)
            }
        })
        tabMenu.setupWithViewPager(vpMenu)

        vpMenu.currentItem = 1
        vpMenu.currentItem = 0
    }

    fun inflateCustomTabs() {
        for (i in 0 until tabMenu.tabCount) {
            val tab = tabMenu.getTabAt(i)
            tab?.customView = null
            tab?.customView = mTabAdapter?.getTabView(i, requireContext())
        }
    }

    fun inflateCustomTabSelected(selectedPosition: Int) {
        val tab = tabMenu.getTabAt(selectedPosition)
        tab?.customView = null
        tab?.customView = mTabAdapter?.getSelectedTabView(selectedPosition, requireContext())
    }
}