plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("nexu.android.feature")
    id("nexu.android.library.compose")
}

android {
    namespace = "com.nexu.feature.todohome"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core:common"))
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}