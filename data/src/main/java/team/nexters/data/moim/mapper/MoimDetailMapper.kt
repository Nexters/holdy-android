package team.nexters.data.moim.mapper

import team.nexters.data.moim.model.LoginUserResponse
import team.nexters.data.moim.model.MoimDetailRequest
import team.nexters.data.moim.model.MoimDetailResponse
import team.nexters.data.moim.model.ParticipantResponse
import team.nexters.domain.moim.model.LoginUser
import team.nexters.domain.moim.model.MoimDetailModel
import team.nexters.domain.moim.model.Participant
import team.nexters.domain.moim.usecase.GetMoimDetailUseCase

internal fun GetMoimDetailUseCase.Param.toData() = MoimDetailRequest(
    id = id
)

internal fun MoimDetailResponse.toDomain() = MoimDetailModel(
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