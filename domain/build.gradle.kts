plugins {
    id("com.android.library")
    id("kotlin-android")
    id("de.mannodermaus.android-junit5")
    id("kotlin-kapt")
}

android {
    compileSdk = Apps.compileSdk
}

dependencies {
    kapt(Dependencies.Hilt.Kapt)
    implementation(Dependencies.Hilt.Core)
    Dependencies.Essential.forEach(::implementation)
    Dependencies.Test.forEach(::testImplementation)
}