package team.nexters.domain.hold.model

import team.nexters.domain.moim.model.MoimModel

data class NewHold(
    val id: Long,
    val order: Int,
    val moim: MoimModel
)