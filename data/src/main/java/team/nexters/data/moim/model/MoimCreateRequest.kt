package team.nexters.data.moim.model

data class MoimCreateRequest(
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