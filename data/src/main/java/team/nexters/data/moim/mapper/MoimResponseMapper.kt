package team.nexters.data.moim.mapper

import team.nexters.data.moim.model.HostResponse
import team.nexters.data.moim.model.MoimResponse
import team.nexters.data.moim.model.PlaceResponse
import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.model.Place
import team.nexters.domain.moim.model.Host


internal fun MoimResponse.toDomain() = MoimModel(
    id = id,
    host = host.toDomain(),
    place = place.toDomain(),
    startDate = startDate,
    endDate = endDate

)

fun HostResponse.toDomain() = Host(
    nickname = nickname
)

fun PlaceResponse.toDomain() = Place(
    summary = summary,
    address = address,
    mapLink = mapLink
)
