package team.nexters.semonemo.ui.home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.content.FileProvider
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import team.nexters.semonemo.R
import team.nexters.semonemo.theme.SemoNemoTheme
import team.nexters.semonemo.ui.home.navigation.HomeNavigation
import team.nexters.semonemo.ui.home.navigation.HomeScreens
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemoNemoTheme {
                val navController = rememberNavController()
                HomeNavigation(navController = navController)
            }
        }
    }

    fun onShareButtonClicked(stickerBitmap: Bitmap) {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            val stickerUri = saveImageAtCacheDir(stickerBitmap) ?: Uri.EMPTY
            val backgroundUri = saveImageAtCacheDir(drawableToBitmap(R.drawable.gray_background))
            shareInstagram(stickerUri, backgroundUri)
        } else {
            permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    private fun drawableToBitmap(@DrawableRes drawableRes: Int): Bitmap {
        val drawable = getDrawable(drawableRes)
        val bitmapDrawable = drawable as BitmapDrawable
        return bitmapDrawable.bitmap
    }

    private fun shareInstagram(stickerUri: Uri?, backgroundUri: Uri?) {
        val sourceApplication = "team.nexters.semonemo"
        val launchIntent = packageManager.getLaunchIntentForPackage("com.instagram.android")
        if (launchIntent == null) {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + "com.instagram.android")
            )
            startActivity(intent)
        } else {
            val intent = Intent("com.instagram.share.ADD_TO_STORY").apply {
                putExtra("source_application", sourceApplication)
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
                setDataAndType(backgroundUri, "image/*")
                putExtra("interactive_asset_uri", stickerUri)
            }
            grantUriPermission(
                "com.instagram.android", stickerUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            grantUriPermission(
                "com.instagram.android", backgroundUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            packageManager.resolveActivity(intent, 0)?.let {
                this.startActivity(intent)
            }
        }
    }

    private fun saveImageAtCacheDir(bitmap: Bitmap): Uri? {
        val fileName = System.currentTimeMillis().toString() + ".png"
        val cachePath = "$cacheDir/file"
        val dir = File(cachePath)

        if (dir.exists().not()) {
            dir.mkdirs() // 폴더 없을경우 폴더 생성
        }

        val fileItem = File("$dir/$fileName")
        try {
            fileItem.createNewFile()
            val fos = FileOutputStream(fileItem)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.close()

            MediaScannerConnection.scanFile(
                applicationContext,
                arrayOf(fileItem.toString()),
                null,
                null
            )
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }


        return FileProvider.getUriForFile(
            applicationContext,
            "team.nexters.semonemo.fileprovider",
            fileItem
        )
    }
}
