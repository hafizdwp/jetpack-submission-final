package me.hafizdwp.jetpack_submission_final

import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.main_fragment.*
import me.hafizdwp.jetpack_submission_final.base.BaseFragment
import me.hafizdwp.jetpack_submission_final.base.BasePagerAdapter
import me.hafizdwp.jetpack_submission_final.ui.MovieSliderFragment
import me.hafizdwp.jetpack_submission_final.ui.movie.MovieFragment
import me.hafizdwp.jetpack_submission_final.ui.tv_show.TvShowFragment

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

    var mSliderAdapter: BasePagerAdapter? = null
    var mTabAdapter: MainSliderAdapter? = null


    override fun onExtractArguments() {
    }

    override fun onReady() {

        setupSlider()
        setupMenuTabs()
    }

    fun setupSlider() {
        mSliderAdapter = BasePagerAdapter(childFragmentManager)
        (0..5).forEach {
            mSliderAdapter?.addFragment(
                    fragment = MovieSliderFragment.newInstance()
            )
        }
        vpSlider.adapter = mSliderAdapter
        tabSlider.setupWithViewPager(vpSlider)
    }

    fun setupMenuTabs() {
        mTabAdapter = MainSliderAdapter(childFragmentManager)
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

        vpMenu.currentItem = 0
        vpMenu.currentItem = 0
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