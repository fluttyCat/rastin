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
    implementation(project(":core:common"))
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}