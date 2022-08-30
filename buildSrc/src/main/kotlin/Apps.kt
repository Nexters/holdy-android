import org.gradle.api.JavaVersion

object Apps {
    const val minSdk = 23
    const val targetSdk = 32
    const val compileSdk = 32
    const val jvmTarget = "11"
    const val versionCode = 2
    const val versionName = "1.0.1"
    val targetCompat = JavaVersion.VERSION_11
    val sourceCompat = JavaVersion.VERSION_11
}
