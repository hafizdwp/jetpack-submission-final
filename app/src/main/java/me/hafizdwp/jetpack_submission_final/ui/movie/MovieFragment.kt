package me.hafizdwp.jetpack_submission_final.ui.movie

import androidx.lifecycle.LifecycleOwner
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment

class MovieFragment : BaseFragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    override val layoutRes: Int
        get() = R.layout.movie_fragment
    override val mLifecycleOwner: LifecycleOwner
        get() = this

    override fun onExtractArguments() {
    }

    override fun onReady() {

        setupSlider()
    }

    private fun setupSlider() {

    }
}
