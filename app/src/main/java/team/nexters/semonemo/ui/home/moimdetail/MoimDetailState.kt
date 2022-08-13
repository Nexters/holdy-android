package team.nexters.semonemo.ui.home.moimdetail

import team.nexters.domain.moim.model.MoimDetailModel

sealed class MoimDetailState {
    object Empty: MoimDetailState()
    data class Success(val moimDetailModel: MoimDetailModel): MoimDetailState()
}