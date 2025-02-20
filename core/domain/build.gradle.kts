plugins {
    id("rastin.android.library")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.rastin.android.core.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation("io.ktor:ktor-client-core:2.3.3")
    implementation("io.ktor:ktor-client-websockets:2.3.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.persianDate)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
