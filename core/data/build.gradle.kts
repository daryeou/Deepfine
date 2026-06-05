val compileSdkValue: Int by rootProject.extra
val minSdkValue: Int by rootProject.extra
val compatibilityValue: JavaVersion by rootProject.extra

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.wonjo.deepfine.core.data"
    compileSdk {
        version = release(compileSdkValue) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = minSdkValue

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = compatibilityValue
        targetCompatibility = compatibilityValue
    }

}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
