package me.hafizdwp.jetpack_submission_final.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_slider_fragment.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseFragment
import me.hafizdwp.jetpack_submission_final.data.Const
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.ui.detail.DetailActivity
import me.hafizdwp.jetpack_submission_final.utils.withArgs
import me.hafizdwp.jetpack_submission_final.utils.withLoadingPlaceholder

/**
 * @author hafizdwp
 * 30/11/2019
 **/
class MainSliderFragment : BaseFragment() {

    companion object {
        const val ARGS_SLIDER_DATA = "args_slider_data"

        fun newInstance(data: Movreak) = MainSliderFragment().withArgs {
            putParcelable(ARGS_SLIDER_DATA, data)
        }
    }

    override val layoutRes: Int
        get() = R.layout.main_slider_fragment
    override val mLifecycleOwner: LifecycleOwner
        get() = this

    var mData: Movreak? = null


    override fun onExtractArguments() {
        mData = arguments?.getParcelable(ARGS_SLIDER_DATA)
    }

    @SuppressLint("SetTextI18n")
    override fun onReady() {
        Glide.with(requireContext())
                .load(Const.BASE_IMAGE_PATH_W780 + (mData?.backdropPath ?: ""))
                .withLoadingPlaceholder(requireContext())
                .into(imageView)
        textTitle.text = mData?.title
        textOverview.text = mData?.overview
        ratingBar.rating = ((mData?.rating ?: 0.0) / 2).toFloat()
        textRating.text = "${(mData?.rating ?: 0)} / 10"

        rootConstraint.setOnClickListener {
            mData?.let { it1 ->
                DetailActivity.startActivity(
                        context = requireContext(),
                        data = it1
                )
            }
        }
    }
}