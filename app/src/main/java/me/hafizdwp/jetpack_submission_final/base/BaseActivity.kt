package me.hafizdwp.jetpack_submission_final.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * @author hafizdwp
 * 30/11/2019
 **/
abstract class BaseActivity : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutRes: Int

    abstract fun onExtractIntents()
    abstract fun onReady()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        onExtractIntents()
        onReady()
    }
}