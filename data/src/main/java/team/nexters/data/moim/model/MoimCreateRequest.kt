package team.nexters.data.moim.model

import com.google.gson.annotations.SerializedName

data class MoimCreateRequest(
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("place")
    val place: PlaceResponse
)