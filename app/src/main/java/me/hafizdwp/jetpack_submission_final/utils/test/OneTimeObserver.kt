package me.hafizdwp.jetpack_submission_final.utils.test

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer

class OneTimeObserver<T>(private val handler: (T) -> Unit?) : Observer<T>, LifecycleOwner {

    private val lifeCycle = LifecycleRegistry(this)

    init {
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onChanged(t: T) {
        handler(t)
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun getLifecycle(): Lifecycle = lifeCycle
}