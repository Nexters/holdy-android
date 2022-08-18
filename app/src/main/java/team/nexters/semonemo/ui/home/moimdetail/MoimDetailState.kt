package team.nexters.semonemo.ui.home.moimdetail

import team.nexters.domain.moim.model.MoimModel

data class MoimDetailState(
    val moimDetailModel: MoimModel? = null,
    val contentLoading: Boolean = false,
    val loading: Boolean = true
)