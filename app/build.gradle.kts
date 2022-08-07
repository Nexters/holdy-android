plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    compileSdk = Apps.compileSdk
    namespace = "team.nexters.semonemo"
    defaultConfig {
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compiler.Compose
    }

    kotlinOptions {
        jvmTarget = Apps.jvmTarget
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${rootProject.file(".").absolutePath}/report/compose-reports"
        )
    }
}

dependencies {
    implementation(project(Modules.Data))
    implementation(project(Modules.Domain))
    implementation(project(Modules.Shared))

    implementation("com.google.firebase:firebase-common-ktx:20.1.1")
    kapt(Dependencies.Hilt.Kapt)
    implementation(Dependencies.Hilt.Android)
    implementation(Dependencies.Jetpack.DataStore)
    Dependencies.Essential.forEach(::implementation)
    Dependencies.Compose.forEach(::implementation)
    Dependencies.Ktx.forEach(::implementation)

    implementation(platform(Dependencies.Firebase.Bom))
    Dependencies.Firebase.List.forEach(::implementation)

    Dependencies.Rx.forEach(::implementation)
}
