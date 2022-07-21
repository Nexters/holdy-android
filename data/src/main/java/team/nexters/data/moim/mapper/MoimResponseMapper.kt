package team.nexters.data.moim.mapper

import team.nexters.data.moim.model.MoimResponse
import team.nexters.domain.moim.model.MoimModel

internal fun MoimResponse.toDomain() = MoimModel(
    id = id,
    host = host.toDomain(),
    place = place.toDomain(),
    startDate = startDate,
    endDate = endDate

)

private fun MoimResponse.Host.toDomain() = MoimModel.Host(
    nickname = nickname
)

private fun MoimResponse.Place.toDomain() = MoimModel.Place(
    summary = summary,
    address = address,
    mapLink = mapLink
)
