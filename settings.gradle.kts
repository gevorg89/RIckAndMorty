pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

//    plugins {
//        val kotlinVersion = extra["kotlin.version"] as String
//        val agpVersion = extra["agp.version"] as String
//        val composeVersion = extra["compose.version"] as String
//
//        kotlin("jvm").version(kotlinVersion)
//        kotlin("multiplatform").version(kotlinVersion)
//        kotlin("android").version(kotlinVersion)
//
//        id("com.android.application").version(agpVersion)
//        id("com.android.library").version(agpVersion)
//
//        id("org.jetbrains.compose").version(composeVersion)
//    }
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
include(":shared")
include("common:core")

include("common:character:api")
include("common:character:data")
include("common:character:presentation")

include("common:episode:api")
include("common:episode:data")
include("common:episode:presentation")

include("common:umbrella")