package me.hafizdwp.jetpack_submission_final

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import me.hafizdwp.jetpack_submission_final.base.BaseActivity
import me.hafizdwp.jetpack_submission_final.ui.MainActivity

/**
 * @author hafizdwp
 * 02/12/2019
 **/
class SplashscreenActivity : BaseActivity() {

    override val layoutRes: Int
        get() = R.layout.splashscreen_activity

    override fun onExtractIntents() {
    }

    override fun onReady() {
        runBlocking {
            delay(1000L)
            MainActivity.startActivity(this@SplashscreenActivity)
            this@SplashscreenActivity.finish()
        }
    }
}