package team.nexters.data.hold.mapper

import team.nexters.data.hold.model.NewStamp
import team.nexters.data.hold.model.Stamp
import team.nexters.data.moim.mapper.toDomain
import team.nexters.domain.hold.model.Hold

fun Stamp.toDomain(): Hold {
    return Hold(
        id = this.id,
        moim = this.meeting.toDomain()
    )
}