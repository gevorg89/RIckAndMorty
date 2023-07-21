plugins {
    kotlin("multiplatform")
    id("multiplatform-compose-setup")
    id("android-setup").apply(false)
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:character:data"))
            }
        }

        androidMain {
            dependencies {
//                implementation(Dependencies.Ktor.android)
            }
        }

        iosMain {
            dependencies {
//                implementation(Dependencies.Ktor.ios)
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