package team.nexters.data.extension

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response
import team.nexters.data.base.BaseResponse
import team.nexters.data.base.ErrorResponse
import team.nexters.data.datastore.DataStoreManager
import team.nexters.data.user.model.response.LoginResponse

internal inline fun <reified T> Response<BaseResponse<T>>.apiCall(): T {
    val body = body()
    return if (isSuccessful && body != null) {
        body.data ?: throw IllegalStateException(body.message)
    } else {
        throw IllegalStateException(errorBody()?.use { it.handleErrorResponse().message })
    }
}

internal suspend fun Response<BaseResponse<LoginResponse>>.headerIntercept(
    dataStoreManager: DataStoreManager,
): Response<BaseResponse<LoginResponse>> {
    val body = checkNotNull(body())
    if (body.data != null) {
        val headers = headers()
        val cookie = checkNotNull(headers["Set-Cookie"])
        dataStoreManager.setSession(cookie)
    }
    return this
}

fun ResponseBody.handleErrorResponse(): ErrorResponse =
    Gson().fromJson(this.string(), ErrorResponse::class.java)
