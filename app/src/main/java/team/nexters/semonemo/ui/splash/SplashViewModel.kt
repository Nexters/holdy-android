package team.nexters.semonemo.ui.splash

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _navigate = MutableLiveData<Navigation>()
    val navigate
        get() = _navigate

    private val _deepLink = MutableLiveData<Uri>()

    fun updateDeepLink(deepLink: Uri) {

    }

}