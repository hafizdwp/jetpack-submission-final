package me.hafizdwp.jetpack_submission_final.ui

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseActivity
import me.hafizdwp.jetpack_submission_final.ui.favorite.FavoriteFragment
import me.hafizdwp.jetpack_submission_final.utils.toastSpammable

/**
 * @author hafizdwp
 * 30/11/2019
 **/
class MainActivity : BaseActivity() {

    companion object {
        fun startActivity(context: Context) =
                context.startActivity(Intent(context, MainActivity::class.java))
    }

    override val layoutRes: Int
        get() = R.layout.main_activity

    val mFragmentMenuList = listOf<Fragment>(
            MainFragment.newInstance(),
            FavoriteFragment.newInstance()
    )


    override fun onExtractIntents() {

    }

    override fun onReady() {

        setupBottomNav()
    }

    fun setupBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bnav_main -> selectFragment(mFragmentMenuList[0])
                R.id.bnav_favorite -> selectFragment(mFragmentMenuList[1])
            }
            return@setOnNavigationItemSelectedListener true
        }
        selectFragment(mFragmentMenuList[0])
    }

    fun selectFragment(fragment: Fragment) {

        val tag = when (fragment) {
            is MainFragment -> MainFragment::class.java.simpleName
            is FavoriteFragment -> FavoriteFragment::class.java.simpleName
            else -> ""
        }

        supportFragmentManager.beginTransaction().apply {
            if (supportFragmentManager.findFragmentByTag(tag) == null) {
                add(R.id.frameLayout, fragment, tag)
            }

            show(fragment)

            mFragmentMenuList.forEach {
                it.takeIf { it != fragment }?.let { fragmentToHide ->
                    hide(fragmentToHide)
                }
            }
        }.commit()
    }

    override fun onBackPressed() {
        handleBackPressed()
    }

    private fun handleBackPressed() {
        when {
            bottomNav.selectedItemId != R.id.bnav_main -> bottomNav.selectedItemId = R.id.bnav_main
            bottomNav.selectedItemId == R.id.bnav_main -> checkIsDoublePressed()
            else -> checkIsDoublePressed()
        }
    }

    private fun checkIsDoublePressed() {
        if (isDoubleBackPressed) {
            super.onBackPressed()
        } else {
            isDoubleBackPressed = true
            toastSpammable(getString(R.string.double_exit))
            GlobalScope.launch {
                delay(2000L)
                isDoubleBackPressed = false
            }
        }
    }

    var isDoubleBackPressed = false

}
