package team.nexters.semonemo.ui.splash

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.theme.SemoNemoTheme
import team.nexters.semonemo.theme.Tertiary
import timber.log.Timber


class SplashActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDeepLink()
        setContent {
            SemoNemoTheme {
                SplashScreen()
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initDeepLink()
    }

    private fun initDeepLink() {
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData: PendingDynamicLinkData? ->
                if (pendingDynamicLinkData != null) {
                    val deepLink = pendingDynamicLinkData.link
                    Timber.d("tag1 deepLink $deepLink")
                    //TODO
                }
            }
            .addOnFailureListener(this) { e -> Timber.d(e, "getDynamicLink:onFailure") }
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


