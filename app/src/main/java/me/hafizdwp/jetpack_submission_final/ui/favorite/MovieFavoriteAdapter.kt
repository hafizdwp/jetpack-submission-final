package me.hafizdwp.jetpack_submission_final.ui.favorite

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_content_item.view.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseRecyclerAdapter
import me.hafizdwp.jetpack_submission_final.data.Const
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.ui.ContentActionListener
import me.hafizdwp.jetpack_submission_final.utils.withLoadingPlaceholder

/**
 * @author hafizdwp
 * 02/12/2019
 **/
class MovieFavoriteAdapter(val items: List<Movreak>,
                           val actionListener: ContentActionListener) : BaseRecyclerAdapter<Movreak>() {

    override val bindItemLayoutRes: Int?
        get() = R.layout.main_content_item
    override val mListItem: List<Movreak>
        get() = items

    override fun onBind(itemView: View, model: Movreak) {
        itemView.apply {
            itemView.apply {
                Glide.with(itemView.context)
                        .load(Const.BASE_IMAGE_PATH_W300 + model.posterPath)
                        .withLoadingPlaceholder(itemView.context)
                        .into(imageView)

                textTitle.text = model.title
                textRating.text = model.rating.toString()

                rootView.setOnClickListener {
                    actionListener.onItemClick(model)
                }
            }
        }
    }
}