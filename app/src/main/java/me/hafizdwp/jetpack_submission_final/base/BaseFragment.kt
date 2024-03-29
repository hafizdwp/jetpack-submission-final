package me.hafizdwp.jetpack_submission_final.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    abstract val layoutRes: Int
    abstract val mLifecycleOwner: LifecycleOwner

    abstract fun onExtractArguments()
    abstract fun onReady()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onExtractArguments()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onReady()
    }

    fun <T> LiveData<T>.observe(
            action: (T?) -> Unit) {
        observe(mLifecycleOwner, Observer {
            action.invoke(it)
        })
    }
}
