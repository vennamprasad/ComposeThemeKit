plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    testImplementation(libs.mockk)
    testImplementation(libs.junit.jupiter.api)
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
}