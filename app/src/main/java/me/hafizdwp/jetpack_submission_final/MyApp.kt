package me.hafizdwp.jetpack_submission_final

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * @author hafizdwp
 * 11/11/2019
 **/
class MyApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApp? = null

        fun getContext() = instance?.applicationContext
    }

    override fun onCreate() {
        super.onCreate()

        // Stetho
        Stetho.initializeWithDefaults(this)
    }
}