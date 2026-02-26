plugins {
    alias(libs.plugins.android.library)
//    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.myapplication.feature.settings.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
            )
        }
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
    testImplementation(libs.mockk)
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit.jupiter.api)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}