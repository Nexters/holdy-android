package team.nexters.data.moim.mapper

import team.nexters.data.moim.model.MoimCreateRequest
import team.nexters.data.moim.model.MoimCreateResponse
import team.nexters.data.moim.model.PlaceResponse
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.usecase.CreateMoimUseCase

internal fun CreateMoimUseCase.Param.toData() = MoimCreateRequest(
    startDate = startDate,
    endDate = endDate,
    place = place.toData()
)

internal fun CreateMoimUseCase.Place.toData() = PlaceResponse(
    summary = summary,
    address = address,
    mapLink = mapLink
)

internal fun MoimCreateResponse.toDomain() = MoimResponseModel(
    id = id
)