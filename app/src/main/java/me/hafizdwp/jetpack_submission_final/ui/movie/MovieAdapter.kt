package me.hafizdwp.jetpack_submission_final.ui.movie

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseRecyclerAdapter
import me.hafizdwp.jetpack_submission_final.data.Const
import me.hafizdwp.jetpack_submission_final.data.source.Movreak
import me.hafizdwp.jetpack_submission_final.utils.withLoadingPlaceholder

class MovieAdapter(val items: List<Movreak>) : BaseRecyclerAdapter<Movreak>() {

    override val bindItemLayoutRes: Int?
        get() = R.layout.movie_item
    override val mListItem: List<Movreak>
        get() = items

    override fun onBind(itemView: View, model: Movreak) {
        itemView.apply {
            Glide.with(itemView.context)
                    .load(Const.BASE_IMAGE_PATH_W300 + model.posterPath)
                    .withLoadingPlaceholder(itemView.context)
                    .into(imageView)

            textTitle.text = model.title
            textRating.text = model.rating.toString()
        }
    }
}
