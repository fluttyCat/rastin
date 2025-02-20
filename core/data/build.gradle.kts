plugins {
    id("rastin.android.library")
    id("rastin.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.rastin.android.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.okhttp.logging)
    implementation(libs.androidx.documentfile)
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
}
