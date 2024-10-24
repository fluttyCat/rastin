plugins {
    id("nexu.android.library")
    id("nexu.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.nexu.android.core.data.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
}