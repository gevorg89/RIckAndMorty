plugins {
    id("multiplatform-setup")
    id("android-setup").apply(false)
    kotlin("plugin.serialization")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":common:character:api"))
                implementation(project(":common:core"))
            }
        }
    }
}

android {
    namespace = "com.gevorg89.rickandmorty.character.data"
    compileSdk = 33

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}