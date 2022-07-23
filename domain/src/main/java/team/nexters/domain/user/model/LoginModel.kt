package team.nexters.domain.user.model

data class LoginModel(
    val result: String,
    val loginUser: LoginUser?
) {
    data class LoginUser(
        val nickname: String
    )
}

