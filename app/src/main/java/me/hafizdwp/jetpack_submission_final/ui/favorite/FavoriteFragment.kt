package me.hafizdwp.jetpack_submission_final.ui.favorite

import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment

class FavoriteFragment : BaseFragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override val layoutRes: Int
        get() = R.layout.favorite_fragment

    override fun onExtractArguments() {
    }

    override fun onReady() {
    }
}
