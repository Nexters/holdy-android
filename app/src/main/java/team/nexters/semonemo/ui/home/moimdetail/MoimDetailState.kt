package team.nexters.semonemo.ui.home.moimdetail

import team.nexters.domain.moim.model.MoimModel

sealed class MoimDetailState {
    object Empty: MoimDetailState()
    data class Success(val moimDetailModel: MoimModel): MoimDetailState()
}