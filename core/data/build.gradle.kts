plugins {
    id("nexu.android.library")
    id("nexu.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.nexu.android.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))

    implementation(libs.androidx.documentfile)
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
}
