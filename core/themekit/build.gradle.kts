plugins {
    alias(libs.plugins.android.library)
    //    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "prasad.vennam.themekit"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Exporting all theme modules so consumers only need this one dependency!
    api(project(":core:model"))
    api(project(":core:domain"))
    api(project(":core:data"))
    api(project(":core:datastore"))
    api(project(":core:designsystem"))
    api(project(":feature:settings:presentation"))
}
