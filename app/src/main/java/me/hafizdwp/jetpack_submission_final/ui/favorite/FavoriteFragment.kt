package me.hafizdwp.jetpack_submission_final.ui.favorite

import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.favorite_fragment.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment
import me.hafizdwp.jetpack_submission_final.ui.MainMenuAdapter
import me.hafizdwp.jetpack_submission_final.utils.call
import me.hafizdwp.jetpack_submission_final.utils.obtainViewModel

class FavoriteFragment : BaseFragment() {

    companion object {
        const val CODE_SHOULD_REFRESH_DATA = 1000
        fun newInstance() = FavoriteFragment()
    }

    override val layoutRes: Int
        get() = R.layout.favorite_fragment
    override val mLifecycleOwner: LifecycleOwner
        get() = this

    val mViewModel by lazy { obtainViewModel<FavoriteViewModel>() }
    var mTabAdapter: MainMenuAdapter? = null


    override fun onExtractArguments() {
    }

    override fun onReady() {

        setupTabs()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            mViewModel.shouldRefreshData.call()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == CODE_SHOULD_REFRESH_DATA) {
            mViewModel.shouldRefreshData.call()
        }
    }

    fun setupTabs() {
        mTabAdapter = MainMenuAdapter(childFragmentManager)
        mTabAdapter?.addFragment(
                fragment = MovieFavoriteFragment.newInstance(),
                title = getString(R.string.movie)
        )
        mTabAdapter?.addFragment(
                fragment = TvShowFavoriteFragment.newInstance(),
                title = getString(R.string.tv_show)
        )

        viewPager.adapter = mTabAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                inflateCustomTabs()
                inflateCustomTabSelected(position)
            }
        })
        tabLayout.setupWithViewPager(viewPager)

        // Workaround to get the custom tabs shown
        viewPager.currentItem = 1
        viewPager.currentItem = 0
    }

    fun inflateCustomTabs() {
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            tab?.customView = null
            tab?.customView = mTabAdapter?.getTabView(i, requireContext())
        }
    }

    fun inflateCustomTabSelected(selectedPosition: Int) {
        val tab = tabLayout.getTabAt(selectedPosition)
        tab?.customView = null
        tab?.customView = mTabAdapter?.getSelectedTabView(selectedPosition, requireContext())
    }
}
