plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Apps.compileSdk
}

dependencies {
    api(Dependencies.Timber)
    kapt(Dependencies.Hilt.Kapt)
    implementation(Dependencies.Hilt.Core)
    Dependencies.Essential.forEach(::implementation)

}