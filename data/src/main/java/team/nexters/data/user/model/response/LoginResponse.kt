package team.nexters.data.user.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("result")
    val result: String,
    @SerializedName("loginUser")
    val loginUser: LoginUser?
) {
    data class LoginUser(
        @SerializedName("nickname")
        val nickname: String
    )
}
