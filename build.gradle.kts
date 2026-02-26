// Top-level build file

plugins {
    // Android
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    // Kotlin
    // alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false

    // KSP (Room / Hilt)
    alias(libs.plugins.ksp) apply false

    // Hilt
    alias(libs.plugins.hilt) apply false

    // Compose Compiler Plugin (required for Kotlin 2.0+)
    alias(libs.plugins.kotlin.compose) apply false

    // Detekt
    alias(libs.plugins.detekt)
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(from = rootProject.file("publish-module.gradle"))

    detekt {
        val detektConfigFile = file("$rootDir/config/detekt/detekt.yml")
        if (detektConfigFile.exists()) {
            config.setFrom(files(detektConfigFile))
        }
        buildUponDefaultConfig = true
        ignoreFailures = true
        autoCorrect = true
    }
    
    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

