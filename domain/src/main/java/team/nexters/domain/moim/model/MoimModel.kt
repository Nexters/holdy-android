package team.nexters.domain.moim.model

data class MoimModel(
    val id: Int,
    val host: Host,
    val place: Place,
    val startDate: String,
    val endDate: String,
)

data class Host(
    val nickname: String
)

data class Place(
    val summary: String,
    val address: String,
    val mapLink: String,
)