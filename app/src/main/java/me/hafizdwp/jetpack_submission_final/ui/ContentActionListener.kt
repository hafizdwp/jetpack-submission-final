package me.hafizdwp.jetpack_submission_final.ui

import android.widget.ImageView
import me.hafizdwp.jetpack_submission_final.data.model.Movreak

/**
 * @author hafizdwp
 * 02/12/2019
 **/
interface ContentActionListener {
    fun onItemClick(data: Movreak, imgView: ImageView)
}