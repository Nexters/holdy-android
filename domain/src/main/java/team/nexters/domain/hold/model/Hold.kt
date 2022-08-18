package team.nexters.domain.hold.model

import team.nexters.domain.moim.model.MoimModel

data class Hold(
    val id: Long,
    val moim: MoimModel
)