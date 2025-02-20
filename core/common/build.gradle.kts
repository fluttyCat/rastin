plugins {
    id("rastin.android.library")
    id("rastin.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.rastin.android.core.data.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)

    implementation("io.ktor:ktor-client-core:2.3.3")
    implementation("io.ktor:ktor-client-websockets:2.3.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("io.ktor:ktor-client-okhttp:2.3.3")
    implementation("io.ktor:ktor-client-cio:2.3.3")
    implementation("io.ktor:ktor-client-websockets:2.3.3")
}