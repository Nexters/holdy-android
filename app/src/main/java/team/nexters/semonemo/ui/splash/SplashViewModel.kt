package team.nexters.semonemo.ui.splash

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _navigate = MutableLiveData<Navigation>()
    val navigate
        get() = _navigate

    private val _deepLink = MutableLiveData<Uri>()
    private val _kakaoShare = MutableLiveData<Uri>()

    init {
        viewModelScope.launch {
            // TODO 유저데이터와 딥링크와 짬뽕해서 처리한다음에 navigate 해야함, 일단은 딜레이로 온보딩으로 넘기기만 함
            delay(800)
            _navigate.value = OnBoarding
        }
    }

    fun updateDeepLink(deepLink: Uri) {
        this._deepLink.value = deepLink
    }

    fun updateKakaoShare(kakaoShare: Uri){
        this._kakaoShare.value = kakaoShare
    }

    // TODO
    private fun fetchUser() {

    }

}