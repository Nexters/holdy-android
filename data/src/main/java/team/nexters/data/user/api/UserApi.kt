package team.nexters.data.user.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import team.nexters.data.base.BaseResponse
import team.nexters.data.user.model.request.LoginRequest
import team.nexters.data.user.model.response.LoginResponse

interface UserApi {
    @POST("/api/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<BaseResponse<LoginResponse>>

    @GET("/api/my-info")
    suspend fun getMyInfo(): Response<BaseResponse<LoginResponse>>
}