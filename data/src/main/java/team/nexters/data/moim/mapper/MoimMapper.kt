package team.nexters.data.moim.mapper

import team.nexters.data.moim.model.HostResponse
import team.nexters.data.moim.model.LoginUserResponse
import team.nexters.data.moim.model.MoimRequest
import team.nexters.data.moim.model.MoimResponse
import team.nexters.data.moim.model.ParticipantResponse
import team.nexters.data.moim.model.PlaceResponse
import team.nexters.domain.moim.model.Host
import team.nexters.domain.moim.model.LoginUser
import team.nexters.domain.moim.model.MoimModel
import team.nexters.domain.moim.model.Participant
import team.nexters.domain.moim.model.Place
import team.nexters.domain.moim.usecase.GetMoimDetailUseCase

internal fun GetMoimDetailUseCase.Param.toData() = MoimRequest(
    id = id
)

internal fun MoimResponse.toDomain() = MoimModel(
    id = id,
    startDate = startDate,
    endDate = endDate,
    place = place.toDomain(),
    host = host.toDomain(),
    isEnd = isEnd,
    loginUser = loginUser.toDomain(),
    participants = participants.map {
        it.toDomain()
    }
)


internal fun HostResponse.toDomain() = Host(
    nickname = nickname
)

internal fun PlaceResponse.toDomain() = Place(
    summary = summary,
    address = address,
    mapLink = mapLink
)

internal fun ParticipantResponse.toDomain() = Participant(
    id = id,
    nickname = nickname,
    group = group,
    attend = attend
)

internal fun LoginUserResponse.toDomain() = LoginUser(
    isHost = isHost,
    wantToAttend = wantToAttend
)

