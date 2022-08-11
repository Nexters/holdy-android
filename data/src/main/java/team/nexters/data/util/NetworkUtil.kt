package team.nexters.data.util

import com.google.gson.Gson
import okhttp3.ResponseBody
import team.nexters.data.base.ErrorResponse

object NetworkUtil {
    fun handleErrorResponse(body: ResponseBody): ErrorResponse =
        Gson().fromJson(body.string(), ErrorResponse::class.java)
}

