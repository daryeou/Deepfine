val applicationIdValue: String by rootProject.extra
val compileSdkValue: Int by rootProject.extra
val targetSdkValue: Int by rootProject.extra
val minSdkValue: Int by rootProject.extra
val compatibilityValue: JavaVersion by rootProject.extra
val versionCodeValue: Int by rootProject.extra
val versionNameValue: String by rootProject.extra

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = applicationIdValue
    compileSdk {
        version = release(compileSdkValue) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = applicationIdValue
        minSdk = minSdkValue
        targetSdk = targetSdkValue
        versionCode = versionCodeValue
        versionName = versionNameValue

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = compatibilityValue
        targetCompatibility = compatibilityValue
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":feature:auth"))
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
