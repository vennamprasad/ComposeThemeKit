plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(libs.mockk)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    implementation(libs.kotlinx.serialization.json)
    
    // For @Immutable and @Stable annotations
    compileOnly(libs.compose.ui)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
