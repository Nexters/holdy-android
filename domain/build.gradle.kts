plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("de.mannodermaus.android-junit5")
    id("kotlin-kapt")
}

android {
    compileSdk = Apps.compileSdk
}

dependencies {
    implementation(Dependencies.Hilt)
    Dependencies.Test.forEach(::testImplementation)
}