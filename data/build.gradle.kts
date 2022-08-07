import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("de.mannodermaus.android-junit5")
    id("kotlin-kapt")
}

android {
    compileSdk = Apps.compileSdk
    defaultConfig{
        buildConfigField("String", "API_URL", "\"http://api-dev.semonemo.xyz/\"")
    }
}

dependencies {
    implementation(project(Modules.Domain))
    implementation(project(Modules.Shared))
    kapt(Dependencies.Hilt.Kapt)
    implementation(Dependencies.Hilt.Android)
    implementation(Dependencies.Jetpack.DataStore)
    Dependencies.Test.forEach(::testImplementation)
    Dependencies.Essential.forEach(::implementation)
    Dependencies.Network.forEach(::implementation)
}