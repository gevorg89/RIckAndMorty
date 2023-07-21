plugins {
    id("multiplatform-setup")
    id("android-setup").apply(false)
    kotlin("plugin.serialization")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(Dependencies.Kotlin.Serialization.serialization)
                api(Dependencies.Kotlin.Coroutines.core)

                api(Dependencies.Ktor.core)

                implementation(Dependencies.Ktor.json)
                implementation(Dependencies.Ktor.serialization)
                implementation(Dependencies.Ktor.logging)

                api(Dependencies.Kodein.core)

                api(Dependencies.SqlDelight.core)
            }
        }

        androidMain {
            dependencies {
                implementation(Dependencies.Ktor.android)
                implementation(Dependencies.SqlDelight.android)
            }
        }

        iosMain {
            dependencies {
                implementation(Dependencies.Ktor.ios)
                implementation(Dependencies.SqlDelight.ios)
            }
        }

    }
}

android {
    namespace = "com.gevorg89.rickandmorty.core"
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