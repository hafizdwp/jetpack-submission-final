package me.hafizdwp.jetpack_submission_final.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.custom_main_tab.view.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BasePagerAdapter

/**
 * @author hafizdwp
 * 01/12/2019
 **/
class MainSliderAdapter(fm: FragmentManager) : BasePagerAdapter(fm) {

    fun getTabView(position: Int, context: Context): View {
        return LayoutInflater.from(context).inflate(R.layout.custom_main_tab, null).apply {
            textTabs.text = listTitle[position]
            textTabs.background = null
            textTabs.setTextColor(context.resources.getColor(R.color.white))
        }
    }

    fun getSelectedTabView(position: Int, context: Context): View {
        return LayoutInflater.from(context).inflate(R.layout.custom_main_tab, null).apply {
            textTabs.text = listTitle[position]
            textTabs.setBackgroundResource(R.drawable.custom_bg_oval)
            textTabs.setTextColor(context.resources.getColor(R.color.blackDesign))
        }
    }

}