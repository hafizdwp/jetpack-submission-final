package me.hafizdwp.jetpack_submission_final.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.content_item.view.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.data.Const
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.ui.ContentActionListener
import me.hafizdwp.jetpack_submission_final.utils.withLoadingPlaceholder

/**
 * @author hafizdwp
 * 03/12/2019
 **/
class ContentPagedAdapter(val actionListener: ContentActionListener) : PagedListAdapter<Movreak, ContentPagedAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movreak>() {
            override fun areItemsTheSame(oldMovreak: Movreak, newMovreak: Movreak) =
                    oldMovreak.id == newMovreak.id

            override fun areContentsTheSame(oldMovreak: Movreak, newMovreak: Movreak) =
                    oldMovreak == newMovreak
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.content_item,
                parent, false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(data: Movreak?) {
            itemView.apply {
                Glide.with(itemView.context)
                        .load(Const.BASE_IMAGE_PATH_W300 + data?.posterPath)
                        .withLoadingPlaceholder(itemView.context)
                        .into(imageView)

                textTitle.text = data?.title
                textRating.text = data?.rating.toString()

                rootView.setOnClickListener {
                    data?.let {
                        actionListener.onItemClick(data, imageView)
                    }
                }
            }
        }
    }
}