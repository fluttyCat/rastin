plugins {
    id("nexu.android.library")
    id("nexu.android.hilt")
    id("kotlinx-serialization")
    id("nexu.secrets")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.nexu.android.core.network"
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
}
