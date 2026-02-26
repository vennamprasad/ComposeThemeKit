plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(libs.mockk)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    implementation(libs.kotlin.coroutines.core)
    implementation(project(":core:model"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}
