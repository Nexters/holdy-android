package team.nexters.data.moim.model

import com.google.gson.annotations.SerializedName

data class MoimResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("host")
    val host: HostResponse,
    @SerializedName("place")
    val place: PlaceResponse,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
)

data class HostResponse(
    @SerializedName("nickname")
    val nickname: String
)

data class PlaceResponse(
    @SerializedName("summary")
    val summary: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("mapLink")
    val mapLink: String,
)
