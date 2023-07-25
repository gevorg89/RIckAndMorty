plugins {
    kotlin("multiplatform")
    id("multiplatform-compose-setup")
    id("android-setup").apply(false)
    id("kotlin-parcelize")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":common:core"))
                api(project(":common:character:presentation"))
                implementation(project(":common:character:data"))

            }
        }
    }
}

android {
    namespace = "com.gevorg89.rickandmorty.umbrella"
    compileSdk = 33

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}