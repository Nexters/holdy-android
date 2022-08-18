package team.nexters.data.moim.model.request

import com.google.gson.annotations.SerializedName

data class Attendance(
    @SerializedName("wantToAttend")
    val wantToAttend: Boolean
)

data class HostAttendance(
    @SerializedName("attend")
    val attend: Boolean
)