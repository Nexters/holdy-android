plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Apps.compileSdk

    defaultConfig {
        applicationId = "team.nexters.presentation"
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = Apps.versionCode
        versionName = Apps.versionName
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }

    compileOptions {
        sourceCompatibility = Apps.sourceCompat
        targetCompatibility = Apps.targetCompat
    }

    kotlinOptions {
        jvmTarget = Apps.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.Main
    }
}

dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(Dependencies.Hilt)
    Dependencies.Essential.forEach(::implementation)
    Dependencies.Compose.forEach(::implementation)
    Dependencies.Ktx.forEach(::implementation)
    Dependencies.Compiler.forEach(::kapt)
}