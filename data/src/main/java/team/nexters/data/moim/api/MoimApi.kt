package team.nexters.data.moim.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import team.nexters.data.base.BaseResponse
import team.nexters.data.moim.model.*

interface MoimApi {
    @GET("/api/meetings")
    suspend fun fetchMoimList(): Response<BaseResponse<List<MoimResponse>>>

    @POST("/api/meetings")
    suspend fun createMoim(
        @Body moimCreateRequest: MoimCreateRequest
    ): Response<BaseResponse<MoimCreateResponse>>

    @GET("/api/meetings/{id}")
    suspend fun getMoimDetail(
        @Path("id") moimDetailRequest: Int
    ): Response<BaseResponse<MoimResponse>>
}
