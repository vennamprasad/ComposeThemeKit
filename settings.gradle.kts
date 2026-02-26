pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ThemeKit"

include(":core:model")
include(":core:domain")
include(":core:data")
include(":core:datastore")
include(":core:designsystem")
include(":feature:settings:presentation")

if (System.getenv("JITPACK") != "true") {
    include(":app")
}
