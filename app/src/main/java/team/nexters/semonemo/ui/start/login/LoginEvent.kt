package team.nexters.semonemo.ui.start.login

sealed class LoginEvent{
    object Success : LoginEvent()
    object Failed : LoginEvent()
}
