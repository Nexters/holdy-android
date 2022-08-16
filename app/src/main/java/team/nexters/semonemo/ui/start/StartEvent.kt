package team.nexters.semonemo.ui.start

sealed class StartEvent {
    object LoginSuccess : StartEvent()
    object OnBoardingFinished : StartEvent()
}
