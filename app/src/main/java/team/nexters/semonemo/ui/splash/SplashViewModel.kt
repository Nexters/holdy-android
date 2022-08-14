package team.nexters.semonemo.ui.splash

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.nexters.domain.user.usecase.GetSessionUseCase
import team.nexters.shared.ResultWrapper
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSessionUseCase: GetSessionUseCase
) : ViewModel() {

    private val _navigate = MutableLiveData<Navigation>()
    val navigate
        get() = _navigate

    private val _deepLink = MutableLiveData<Uri>()
    private val _kakaoShare = MutableLiveData<Uri>()

    init {
        viewModelScope.launch {
            delay(300)
            checkSessionExist()
        }
    }

    fun updateDeepLink(deepLink: Uri) {
        this._deepLink.value = deepLink
    }

    fun updateKakaoShare(kakaoShare: Uri) {
        this._kakaoShare.value = kakaoShare
    }


    private fun checkSessionExist() {
        viewModelScope.launch {
            (getSessionUseCase(Unit) as? ResultWrapper.Success)?.let { sessionResultWrapper ->
//                Timber.d("tag1 ${sessionResultWrapper.value}")
                if (sessionResultWrapper.value.isBlank()) {
                    // 유저정보가 없으면 딥링크가 있던 말던 로그인 화면으로 이동한다.
                    _navigate.postValue(Navigation.Login)
                } else {
                    // TODO deeplink
                    _navigate.postValue(Navigation.Home)
                }
            }
        }
    }
}