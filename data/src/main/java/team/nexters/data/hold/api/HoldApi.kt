package team.nexters.data.hold.api

import retrofit2.Response
import retrofit2.http.GET
import team.nexters.data.base.BaseResponse
import team.nexters.data.hold.model.Stamp


interface HoldApi {
    @GET("/api/stamps")
    suspend fun getStamps(): Response<BaseResponse<List<Stamp>>>
}