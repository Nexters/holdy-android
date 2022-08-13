package team.nexters.semonemo

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SemoNemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, appKey=BuildConfig.KAKAO_NATIVE_APP_KEY)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}