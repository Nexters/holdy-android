package team.nexters.data.hold.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import team.nexters.data.base.BaseResponse
import team.nexters.data.hold.model.NewStamp
import team.nexters.data.hold.model.Stamp


interface HoldApi {
    @GET("/api/stamps")
    suspend fun getStamps(): Response<BaseResponse<List<Stamp>>>

    @GET("/api/stamps/new")
    suspend fun getNewStamps(): Response<BaseResponse<List<NewStamp>>>

    @PUT("/api/stamps/new")
    suspend fun getRemovedNewStamps(): Response<BaseResponse<List<NewStamp>>>
}