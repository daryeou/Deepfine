val versionProperties = java.util.Properties().apply {
    load(java.io.FileInputStream("${rootDir}/version.properties"))
}

val versionMain = versionProperties.getProperty("VERSION_MAIN", "1").toInt()
val versionSub = versionProperties.getProperty("VERSION_SUB", "0").toInt()
val versionChild = versionProperties.getProperty("VERSION_CHILD", "0").toInt()

val applicationIdValue by extra("com.wonjo.deepfine")
val compileSdkValue by extra(36)
val targetSdkValue by extra(36)
val minSdkValue by extra(24)
val compatibilityValue by extra(JavaVersion.VERSION_21)
val versionCodeValue by extra(versionMain * 10000 + versionSub * 100 + versionChild)
val versionNameValue by extra("${versionProperties.getProperty("VERSION_MAIN")}.${versionProperties.getProperty("VERSION_SUB")}.${versionProperties.getProperty("VERSION_CHILD")}")

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.ksp) apply false
}
