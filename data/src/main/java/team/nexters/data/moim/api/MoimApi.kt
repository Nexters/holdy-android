package team.nexters.data.moim.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import team.nexters.data.base.BaseResponse
import team.nexters.data.moim.model.MoimCreateRequest
import team.nexters.data.moim.model.MoimCreateResponse
import team.nexters.data.moim.model.MoimResponse

interface MoimApi {
    @GET("/api/meetings")
    suspend fun fetchMoimList(): Response<BaseResponse<List<MoimResponse>>>

    @POST("/api/meetings")
    suspend fun createMoim(
        @Body moimCreateRequest: MoimCreateRequest
    ): Response<BaseResponse<MoimCreateResponse>>
}
