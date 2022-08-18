package team.nexters.data.hold.model

import com.google.gson.annotations.SerializedName
import team.nexters.data.moim.model.response.MoimResponse

data class Stamp(
    @SerializedName("id")
    val id: Long,
    @SerializedName("meeting")
    val meeting: MoimResponse
)