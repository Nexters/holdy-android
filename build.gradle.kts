buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.Essential.Gradle}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.Jetpack.Hilt}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Essential.Kotlin}")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:${Versions.Test.JUnitGradle}")
        classpath("com.google.gms:google-services:4.3.13")
    }
}


allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://oss.jfrog.org/libs-snapshot") }
        maven { url = uri("https://devrepo.kakao.com/nexus/content/groups/public/") }
    }
}

tasks.register("clean", Delete::class) {
    rootProject.buildDir.delete()
}