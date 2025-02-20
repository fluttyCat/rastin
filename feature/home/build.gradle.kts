plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("rastin.android.feature")
    id("rastin.android.library.compose")
}

android {
    namespace = "com.rastin.feature.home"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation("io.ktor:ktor-client-core:2.3.3")
    implementation("io.ktor:ktor-client-websockets:2.3.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("io.ktor:ktor-client-okhttp:2.3.3")
    implementation("io.ktor:ktor-client-cio:2.3.3")
    implementation("io.ktor:ktor-client-websockets:2.3.3")

    implementation(project(":core:common"))
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}