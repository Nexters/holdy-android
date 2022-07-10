plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Apps.compileSdk
}

dependencies {
    implementation(project(Modules.DOMAIN))
    Dependencies.Network.forEach(::implementation)

}