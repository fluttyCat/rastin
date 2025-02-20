plugins {
    id("rastin.android.library")
    id("rastin.android.library.compose")
}

android {
    namespace = "com.rastin.android.core.ui"
}

dependencies {
    implementation(project(":core:designsystem"))

    implementation(project(":core:model"))


    implementation("io.ktor:ktor-client-core:2.3.3")
    implementation("io.ktor:ktor-client-websockets:2.3.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("io.ktor:ktor-client-okhttp:2.3.3")
    implementation("io.ktor:ktor-client-websockets:2.3.3")
    implementation("io.ktor:ktor-client-cio:2.3.3")

    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.coil.video)
    implementation(libs.coil.gif)
    implementation(libs.kotlinx.datetime)

    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.runtime.livedata)
    api(libs.androidx.metrics)
    api(libs.androidx.tracing.ktx)
}