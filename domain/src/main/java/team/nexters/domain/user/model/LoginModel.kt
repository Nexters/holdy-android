package team.nexters.domain.user.model

data class LoginModel(
    val id: Int,
    val nickname: String,
    val group: String,
    val profileImageUrl: String
)

