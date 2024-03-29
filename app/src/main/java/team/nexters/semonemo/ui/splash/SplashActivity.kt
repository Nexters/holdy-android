package team.nexters.semonemo.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import team.nexters.semonemo.BuildConfig
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.Primary
import team.nexters.semonemo.theme.SemoNemoTheme
import team.nexters.semonemo.ui.home.HomeActivity
import team.nexters.semonemo.ui.start.StartActivity
import timber.log.Timber

/**
 * 앱의 진입점은 SplashActivity onCreate or onNewIntent
 * navigation
 */
@AndroidEntryPoint
class SplashActivity() : ComponentActivity() {

    private val vieWModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("tag1 onCreate")
        handleDeepLink()
        handleKakaoShare()
        setContent {
            SemoNemoTheme {
                SplashScreen()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        vieWModel.navigate.observe(this) {
            when (it) {
                Navigation.Login -> {
                    startActivity(StartActivity.newIntent(this))
                }
                is Navigation.Home -> {
                    startActivity(HomeActivity.newIntent(this))
                }
                is Navigation.MoimDetail -> {
                    startActivities(
                        arrayOf(
                            HomeActivity.newIntent(this)
                            //TODO  add BoardActivity
                        )
                    )
                }
            }
            finish()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        Timber.d("tag1 onNewIntent", intent)
        super.onNewIntent(intent)
        handleDeepLink()
        handleKakaoShare()
    }

    // 카카오 공유하기 핸들링
    private fun handleKakaoShare() {
        if (isViaKakaoShare()) {
            intent.data?.let { vieWModel.updateKakaoShare(it) }
        }
    }

    // 카카오 공유하기를 통해서 들어온 경우
    private fun isViaKakaoShare(): Boolean {
        return intent.scheme != null && intent.scheme == "kakao${BuildConfig.KAKAO_NATIVE_APP_KEY}"
    }

    private fun handleDeepLink() {
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData: PendingDynamicLinkData? ->
                pendingDynamicLinkData?.link?.let { deepLink ->
                    Timber.d("tag1 deepLink $deepLink")
                    vieWModel.updateDeepLink(deepLink)
                }
            }
            .addOnFailureListener(this) { e ->
                //TODO 그냥 화면 넘겨야지
                Timber.d(e, "getDynamicLink:onFailure")
            }

    }
}


@Composable
fun SplashScreen() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Primary
        )
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Primary
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.holdy_logo),
                contentDescription = stringResource(R.string.holdy_logo)
            )
        }
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.splash),
            contentDescription = stringResource(R.string.splash)
        )
    }
}


