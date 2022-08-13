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
    val kakaoNativeAppkey = "bce48acc93854a13f254a2c429f23856"
    defaultConfig {
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", "\"$kakaoNativeAppkey\"")
        manifestPlaceholders["KAKAO_NATIVE_APP_KEY"] = kakaoNativeAppkey
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
        }
        debug {

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
    Dependencies.Kakao.List.forEach(::implementation)

    Dependencies.Rx.forEach(::implementation)
}
