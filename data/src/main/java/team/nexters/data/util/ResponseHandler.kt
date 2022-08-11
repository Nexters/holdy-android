package team.nexters.data.util

import retrofit2.Response
import team.nexters.data.base.BaseResponse
import team.nexters.data.datastore.DataStoreManager
import team.nexters.data.user.model.response.LoginResponse
import team.nexters.data.util.NetworkUtil.handleErrorResponse
import team.nexters.shared.ResultWrapper

object ResponseHandler {
    suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<BaseResponse<T>>
    ): ResultWrapper<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                if (body.data != null) {
                    ResultWrapper.Success(body.data)
                } else {
                    ResultWrapper.Error(body.message!!)
                }
            } else {
                ResultWrapper.Error(handleErrorResponse(response.errorBody()!!).message!!)
            }
        } catch (e: Throwable) {
            ResultWrapper.Exception(e)
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

}