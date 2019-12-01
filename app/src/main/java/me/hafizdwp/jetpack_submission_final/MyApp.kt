package me.hafizdwp.jetpack_submission_final

import android.app.Application

/**
 * @author hafizdwp
 * 11/11/2019
 **/
class MyApp : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: MyApp

        fun getContext() = instance.applicationContext
    }
}