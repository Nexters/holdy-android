package team.nexters.data.moim.mapper

import team.nexters.data.moim.model.MoimCreateRequest
import team.nexters.data.moim.model.MoimCreateResponse
import team.nexters.domain.moim.model.MoimCreateModel
import team.nexters.domain.moim.model.MoimResponseModel

internal fun MoimCreateModel.toData() = MoimCreateRequest(
    startDate = startDate,
    endDate = endDate,
    place = place.toData()
)

internal fun MoimCreateModel.Place.toData() = MoimCreateRequest.Place(
    summary = summary,
    address = address,
    mapLink = mapLink
)

internal fun MoimCreateResponse.toDomain() = MoimResponseModel(
    result = result,
    id = saveId
)