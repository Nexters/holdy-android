package team.nexters.domain.moim.model

data class MoimModel(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val isEnd: Boolean,
    val place: Place,
    val host: Host,
    val loginUser: LoginUser,
    val participants: List<Participant>
)

data class LoginUser(
    val isHost: Boolean,
    val wantToAttend: Boolean
)

data class Participant(
    val id: Int,
    val nickname: String,
    val group: String,
    val attended: Boolean,
    val profileImageUrl: String
)
data class Host(
    val nickname: String
)

data class Place(
    val summary: String,
    val address: String,
    val mapLink: String,
)