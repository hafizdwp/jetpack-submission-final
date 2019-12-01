package me.hafizdwp.jetpack_submission_final.utils.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.utils.gone
import me.hafizdwp.jetpack_submission_final.utils.visible

/**
 * @author hafizdwp
 * 01/12/2019
 **/
class MyProgressView(internal var context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    var progressBar: ProgressBar
    var textTitle: TextView
    var textLabel: TextView
    var button: Button
    var imgTooltip: ImageView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_myprogress, null).apply {
            progressBar = findViewById(R.id.progressBar)
            textTitle = findViewById(R.id.textTitle)
            textLabel = findViewById(R.id.textLabel)
            button = findViewById(R.id.button)
            imgTooltip = findViewById(R.id.imgTooltip)
        }

        addView(view)
    }

    fun start() {
        this.visible()
        progressBar.visible()
        imgTooltip.gone()
        textTitle.gone()
        textLabel.gone()
        button.gone()
    }

    fun stopAndGone() {
        this.gone()
    }

    fun stopAndEmpty(emptyTitleMsg: String,
                     emptyLabelMsg: String) {

        imgTooltip.visible()
        progressBar.gone()
        progressBar.gone()
        textTitle.visible()
        textTitle.text = emptyTitleMsg
        textLabel.visible()
        textLabel.text = emptyLabelMsg

        button.gone()
    }

    fun stopAndEmptyFavorite() {

        imgTooltip.visible()
        progressBar.gone()
        progressBar.gone()
        textTitle.visible()
        textTitle.text = context.getString(R.string.empty_favorite)
        textLabel.visible()
        textLabel.text = context.getString(R.string.empty_favorite_msg)

        button.gone()
    }

    fun stopAndError(errorMsg: String = "",
                     withRetryButton: Boolean = true) {

        imgTooltip.gone()
        progressBar.gone()
        textTitle.visible()
        textLabel.visible()
        textLabel.text = errorMsg

        if (withRetryButton)
            button.visible()
    }

    fun setRetryClickListener(todo: () -> Unit) {
        button.setOnClickListener { todo() }
    }
}