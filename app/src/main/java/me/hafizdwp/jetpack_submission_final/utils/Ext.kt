package me.hafizdwp.jetpack_submission_final.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * @author hafizdwp
 * 13/11/2019
 **/

fun Context.myCircularProgressDrawable(): androidx.swiperefreshlayout.widget.CircularProgressDrawable =
        androidx.swiperefreshlayout.widget.CircularProgressDrawable(this).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

fun <T : Drawable> RequestBuilder<T>.withLoadingPlaceholder(context: Context): RequestBuilder<T> {
    return this.apply(
            RequestOptions().placeholder(context.myCircularProgressDrawable()))
}

fun <VM : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<VM>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun <VM : ViewModel> androidx.fragment.app.Fragment.obtainViewModel(viewModelClass: Class<VM>) =
        ViewModelProviders.of(requireActivity(), ViewModelFactory.getInstance(requireActivity().application)).get(viewModelClass)

inline fun <reified T : ViewModel> AppCompatActivity.obtainViewModel() =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(T::class.java)

inline fun <reified VM : ViewModel> androidx.fragment.app.Fragment.obtainViewModel() =
        ViewModelProviders.of(requireActivity(), ViewModelFactory.getInstance(requireActivity().application)).get(VM::class.java)

/**
 * Log ext
 * */
fun log(msg: String,
        tag: String? = null) {
    Log.d(tag ?: "mytag", msg)
}

/**
 * Gson ext
 * ---------------------------------------------------------------------------------------------
 * */
val gson by lazy { Gson() }

inline fun <reified T> makeType() = object : TypeToken<T>() {}.type

fun <T> T.toJson(): String = gson.toJson(this).trim()

inline fun <reified T> String.fromJson(): T = gson.fromJson(this, makeType<T>())

/**
 * LiveData ext
 * ---------------------------------------------------------------------------------------------
 * */

fun <T> MutableLiveData<T>.postCall() {
    postValue(null)
}

/**
 * Coroutine ext
 * ---------------------------------------------------------------------------------------------
 * */

fun launchIO(todo: suspend CoroutineScope.() -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        todo()
    }
}

suspend fun onMain(todo: suspend CoroutineScope.() -> Unit) {
    withContext(Dispatchers.Main) {
        todo()
    }
}

suspend fun <T> asyncAwait(context: CoroutineContext = Dispatchers.IO,
                           action: suspend CoroutineScope.() -> T): T =
        CoroutineScope(context).async { action(this) }.await()

inline fun <reified T> getTag(): String {
    return T::class.java.simpleName
}

/**
 * Ez toast
 * */
var mToast: Toast? = null

fun AppCompatActivity.toast(msg: String) {
    if (msg.isNotBlank()) {
        mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        mToast?.show()
    }
}

fun AppCompatActivity.toastSpammable(msg: String?) {
    if (msg != null) {
        if (msg.isNotBlank()) {
            if (mToast != null) mToast?.cancel()
            mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
            mToast?.show()
        }
    }
}

fun Fragment.toast(msg: String) {
    (requireActivity() as? AppCompatActivity)?.toast(msg)
}

fun Fragment.toastSpammable(msg: String?) {
    (requireActivity() as? AppCompatActivity)?.toastSpammable(msg)
}

fun <T : Fragment> T.withArgs(bundle: Bundle.() -> Unit): Fragment {
    return this.apply {
        arguments = Bundle().apply(bundle)
    }
}

/**
 * View visibility utility
 * */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.isInvisible(): Boolean {
    return visibility == View.INVISIBLE
}

fun View.isGone(): Boolean {
    return visibility == View.GONE
}