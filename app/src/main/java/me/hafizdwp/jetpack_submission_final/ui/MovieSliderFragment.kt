package me.hafizdwp.jetpack_submission_final.ui

import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment

/**
 * @author hafizdwp
 * 30/11/2019
 **/
class MovieSliderFragment : BaseFragment() {

    companion object {
        fun newInstance() = MovieSliderFragment()
    }

    override val layoutRes: Int
        get() = R.layout.main_slider_fragment

    override fun onExtractArguments() {
    }

    override fun onReady() {

    }
}