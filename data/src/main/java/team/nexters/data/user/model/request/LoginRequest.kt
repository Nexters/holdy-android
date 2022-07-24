package team.nexters.data.user.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("authKey")
    val authKey: String
)