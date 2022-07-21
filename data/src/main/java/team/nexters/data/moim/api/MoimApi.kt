package team.nexters.data.moim.api

import retrofit2.Response
import retrofit2.http.GET
import team.nexters.data.moim.model.MoimResponse

interface MoimApi {
    @GET("/api/meetings")
    suspend fun fetchMoimList(): Response<List<MoimResponse>>
}
