package team.nexters.domain.hold.model

import team.nexters.domain.moim.model.MoimDetailModel

data class Hold(
    val id: Long,
    val moimDetail: MoimDetailModel
)