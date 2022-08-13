package team.nexters.semonemo.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.SemoNemoTheme
import team.nexters.semonemo.theme.Tertiary
import team.nexters.semonemo.ui.home.HomeActivity
import team.nexters.semonemo.ui.start.OnBoardingActivity
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
        handleKakaoLink()
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
                OnBoarding -> {
                    startActivity(OnBoardingActivity.newIntent(this))
                }
                is Home -> {
                    startActivity(HomeActivity.newIntent(this))
                }
                is Board -> {
                    startActivities(
                        arrayOf(
                            HomeActivity.newIntent(this)
                            //TODO  add BoardActivity
                        )
                    )
                }
            }
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.none)
            finish()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        Timber.d("tag1 onNewIntent", intent)
        super.onNewIntent(intent)
        handleDeepLink()
        handleKakaoLink()
    }

    private fun handleKakaoLink() {
        intent
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
            color = Tertiary
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Tertiary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
    }
}


