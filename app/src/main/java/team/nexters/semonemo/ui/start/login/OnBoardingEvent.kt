package team.nexters.semonemo.ui.start.login

sealed class OnBoardingEvent{
    object Success : OnBoardingEvent()
    object Failed : OnBoardingEvent()
}
