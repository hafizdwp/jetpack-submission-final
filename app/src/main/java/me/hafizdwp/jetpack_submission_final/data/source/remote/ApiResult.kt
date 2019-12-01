package me.hafizdwp.jetpack_submission_final.data.source.remote

/**
 * @author hafizdwp
 * 01/12/2019
 **/
sealed class ApiResult<out T : Any?> {
    class Success<out T>(val data: T) : ApiResult<T>()
    object Empty : ApiResult<Nothing>()
    class Error(val errorMsg: String) : ApiResult<String>()
}