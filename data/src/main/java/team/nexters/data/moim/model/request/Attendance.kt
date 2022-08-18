package team.nexters.data.moim.model.request

import com.google.gson.annotations.SerializedName

data class Attendance(
    @SerializedName("wantToAttend")
    val wantToAttend: Boolean
)