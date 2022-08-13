package team.nexters.data.moim.model

import com.google.gson.annotations.SerializedName

data class MoimDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("isEnd")
    val isEnd: Boolean,
    @SerializedName("place")
    val place: PlaceResponse,
    @SerializedName("host")
    val host: HostResponse,
    @SerializedName("loginUser")
    val loginUser: LoginUserResponse,
    @SerializedName("participants")
    val participants: List<ParticipantResponse>
)

data class LoginUserResponse(
    @SerializedName("isHost")
    val isHost: Boolean,
    @SerializedName("wantToAttend")
    val wantToAttend: Boolean
)

data class ParticipantResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("group")
    val group: String,
    @SerializedName("attend")
    val attend: Boolean
)