package team.nexters.domain.moim.model

data class MoimCreateModel(
    val startDate: String,
    val endDate: String,
    val place: Place
){
    data class Place(
        val summary: String,
        val address: String,
        val mapLink: String
    )
}