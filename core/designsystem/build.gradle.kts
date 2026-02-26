plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
//    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.myapplication.core.designsystem"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }


    // kotlinOptions { 
    //     jvmTarget = "11" 
    // } 

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.compose.google.fonts)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.junit.jupiter.api)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    api(project(":core:model"))
    implementation(libs.compose.google.fonts)
    implementation(libs.compose.icons.extended)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
}
