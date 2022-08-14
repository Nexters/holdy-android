package team.nexters.data.moim.mapper

import team.nexters.data.moim.model.request.MoimCreateRequest
import team.nexters.data.moim.model.response.MoimId
import team.nexters.data.moim.model.response.PlaceResponse
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

internal fun MoimId.toDomain() = MoimResponseModel(
    id = id
)