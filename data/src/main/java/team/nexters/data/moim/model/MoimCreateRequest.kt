package team.nexters.data.moim.model

import com.google.gson.annotations.SerializedName

data class MoimCreateRequest(
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("place")
    val place: Place
) {
    data class Place(
        @SerializedName("summary")
        val summary: String,
        @SerializedName("address")
        val address: String,
        @SerializedName("mapLink")
        val mapLink: String
    )
}