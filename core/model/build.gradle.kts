@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("nexu.android.library")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    namespace = "com.nexu.android.core.model"
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.documentfile)
}
