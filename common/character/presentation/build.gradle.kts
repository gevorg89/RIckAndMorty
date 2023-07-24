plugins {
    id("multiplatform-compose-setup")
    id("android-setup").apply(false)
    id("org.jetbrains.kotlin.plugin.parcelize")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:character:data"))
                implementation(project(":common:core"))
            }
        }
    }
}

android {
    namespace = "com.gevorg89.rickandmorty.character.presentation"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}