package team.nexters.semonemo.ui.home.moimcreate

sealed class MoimCreateEvent {
    object NavigateToMoimList : MoimCreateEvent()
    object OpenDatePicker : MoimCreateEvent()
    object OpenStartTimePicker : MoimCreateEvent()
    object OpenEndTimePicker : MoimCreateEvent()
    data class CreationFailed(val result: String) : MoimCreateEvent()
}
