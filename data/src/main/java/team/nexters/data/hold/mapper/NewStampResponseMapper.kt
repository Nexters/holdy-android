package team.nexters.data.hold.mapper

import team.nexters.data.hold.model.NewStamp
import team.nexters.data.moim.mapper.toDomain
import team.nexters.domain.hold.model.NewHold

fun NewStamp.toDomain() = NewHold(
    id = id,
    order = order,
    moim = meeting.toDomain()
)