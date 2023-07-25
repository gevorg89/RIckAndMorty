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
                api(project(":common:core"))
            }
        }
    }
}

android {
    namespace = "com.gevorg89.rickandmorty.character.presentation"
    compileSdk = 33
}