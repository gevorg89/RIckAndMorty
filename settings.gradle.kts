pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "RIckAndMorty"
include(":androidApp")
include("common:core")

include("common:character:api")
include("common:character:data")
include("common:character:presentation")

include("common:episode:api")
include("common:episode:data")
include("common:episode:presentation")

include("common:umbrella")