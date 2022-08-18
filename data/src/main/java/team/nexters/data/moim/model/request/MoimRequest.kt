package team.nexters.data.moim.model.request

import com.google.gson.annotations.SerializedName

data class MoimRequest(
    @SerializedName("id")
    val id: Int
)