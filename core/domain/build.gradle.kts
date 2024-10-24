plugins {
    id("nexu.android.library")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.nexu.android.core.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.persianDate)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
