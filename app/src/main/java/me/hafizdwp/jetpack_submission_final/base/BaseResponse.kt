package me.hafizdwp.jetpack_submission_final.base

/**
 * @author hafizdwp
 * 20/11/2019
 **/
data class BaseResponse<T>(

        // success response
        var page: Int?,
        var total_results: Int?,
        var total_pages: Int?,
        var results: T?,

        // exclusive because too lazy too create another model
        var genres: T?,

        // error response
        var status_code: Int?,
        var status_message: String?,
        var success: Boolean?
)