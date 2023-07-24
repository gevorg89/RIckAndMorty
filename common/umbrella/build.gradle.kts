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
                implementation("com.arkivanov.parcelize.darwin:runtime:0.1.4")
            }
        }

        androidMain {
            dependencies {
            }
        }

        iosMain {
            dependencies {
            }
        }


    }
}

android {
    namespace = "com.gevorg89.rickandmorty.umbrella"
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