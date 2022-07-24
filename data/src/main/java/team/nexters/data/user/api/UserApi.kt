package team.nexters.data.user.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import team.nexters.data.user.model.request.LoginRequest
import team.nexters.data.user.model.response.LoginResponse

interface UserApi {
    @POST("/api/login")
    suspend fun login(
        @Body loginRequestModel: LoginRequest
    ): Response<LoginResponse>
}