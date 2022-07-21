package team.nexters.data.moim.model

import com.google.gson.annotations.SerializedName

data class MoimResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("host")
    val host: Host,
    @SerializedName("place")
    val place: Place,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
) {
    data class Host(
        @SerializedName("nickname")
        val nickname: String
    )

    data class Place(
        @SerializedName("summary")
        val summary: String,
        @SerializedName("address")
        val address: String,
        @SerializedName("mapLink")
        val mapLink: String,
    )
}
