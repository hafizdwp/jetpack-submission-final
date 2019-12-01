package me.hafizdwp.jetpack_submission_final.ui.tv_show

import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment

class TvShowFragment : BaseFragment() {

    companion object {
        fun newInstance() = TvShowFragment()
    }

    override val layoutRes: Int
        get() = R.layout.tv_show_fragment

    override fun onExtractArguments() {
    }

    override fun onReady() {
    }
}
