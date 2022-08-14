package team.nexters.data.moim.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import team.nexters.data.base.BaseResponse
import team.nexters.data.moim.model.request.MoimCreateRequest
import team.nexters.data.moim.model.response.MoimId
import team.nexters.data.moim.model.response.MoimResponse

interface MoimApi {
    @GET("/api/meetings")
    suspend fun fetchMoimList(): Response<BaseResponse<List<MoimResponse>>>

    @POST("/api/meetings")
    suspend fun createMoim(
        @Body moimCreateRequest: MoimCreateRequest
    ): Response<BaseResponse<MoimId>>

    @GET("/api/meetings/{id}")
    suspend fun getMoimDetail(
        @Path("id") moimDetailRequest: Int
    ): Response<BaseResponse<MoimResponse>>

    @PUT("/api/meetings/{id}/attendance")
    suspend fun putAttendance(
        @Path("id") id: Int,
        @Body wantToAttend: Boolean
    ): Response<BaseResponse<MoimId>>
}
