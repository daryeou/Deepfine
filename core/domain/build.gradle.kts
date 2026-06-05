val compileSdkValue: Int by rootProject.extra
val minSdkValue: Int by rootProject.extra
val compatibilityValue: JavaVersion by rootProject.extra

plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.wonjo.deepfine.core.domain"
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
    implementation(libs.javax.inject)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
