pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
}

buildCache {
    local {
        directory = "../nexu-prebuilts/m2repository"
    }
}

rootProject.name = "Nexu"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")

include(":core:ui")
include(":core:designsystem")
include(":core:common")
include(":core:model")
include(":core:data")
include(":core:domain")

include(":feature:todohome")
