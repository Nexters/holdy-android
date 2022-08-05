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
        setContent {
            SemoNemoTheme {
                SplashScreen(::createDynamicLink)
            }
        }
        initDeepLink()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initDeepLink()
    }

    private fun createDynamicLink() {

        // 이 딥링크는 가짜입니다. 
        val key = "id"
        val pheedId = "1"

        val dynamicLink = Firebase.dynamicLinks.dynamicLink {
            link = Uri.parse("httsp://teams.nexters.holdy?board=1/")
            domainUriPrefix = "https://holdy.page.link"
            // Open links with this app on Android
            androidParameters {
                DynamicLink.AndroidParameters.Builder(this@SplashActivity.packageName)
                    .setMinimumVersion(1)
                    .build()
            }
        }

        val dynamicLinkUri = dynamicLink.uri
        Timber.d("tag1 dynamicLinkUri $dynamicLinkUri")

    }

    private fun getDeepLink(scheme: String, key: String?, pheedId: String?): Uri {
        return if (key == null) {
            Uri.parse("https://team.nexters.holdy/${scheme}")
        } else {
            Uri.parse("https://team.nexters.holdy/${scheme}/?${key}=$pheedId")
        }
    }

    private fun initDeepLink() {
        val data = intent.data
        Timber.d("tag1 init intent $data")

        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData: PendingDynamicLinkData? ->
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                    val intent = intent
                    val data = intent.data
                    Timber.d("tag1 deepLink $deepLink")
                    Timber.d("tag1 intent $intent")
                    Timber.d("tag1 data $data")

                    val segment = deepLink?.lastPathSegment
                    Timber.d("tag1 segment $segment")

                }


                // Handle the deep link. For example, open the linked
                // content, or apply promotional credit to the user's
                // account.
                // ...

            }
            .addOnFailureListener(this) { e -> Timber.d(e, "getDynamicLink:onFailure") }
    }

}


@Composable
fun SplashScreen(buttonClicks: () -> Unit) {
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

        Button(onClick = { buttonClicks() }, shape = RoundedCornerShape(8.dp), text = "test")
    }
}


