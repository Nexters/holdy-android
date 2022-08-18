package team.nexters.data.hold.model

import com.google.gson.annotations.SerializedName
import team.nexters.data.moim.model.response.MoimResponse

data class NewStamp(
    @SerializedName("id")
    val id: Long,
    @SerializedName("order")
    val order: Int,
    @SerializedName("meeting")
    val meeting: MoimResponse
)