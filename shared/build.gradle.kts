plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = Apps.compileSdk
}

dependencies {
    api(Dependencies.Timber)
}