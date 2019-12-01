package me.hafizdwp.jetpack_submission_final.data.source.remote

/**
 * @author hafizdwp
 * 29/11/2019
 **/
interface ApiCallback<T> {
    fun onSuccess(data: T)
    fun onFailed(e: Exception)
}