package team.nexters.data.moim.model.request

import com.google.gson.annotations.SerializedName
import team.nexters.data.moim.model.response.PlaceResponse

data class MoimCreateRequest(
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("place")
    val place: PlaceResponse
)