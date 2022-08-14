package team.nexters.semonemo.ui.home.moimdetail

sealed class MoimDetailEvent {
    object NavigateToMoimList : MoimDetailEvent()
    data class ShareKaKao(val templeteArgs: Map<String, String>) : MoimDetailEvent()
}
