plugins {
    id("multiplatform-setup")
    id("android-setup").apply(false)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(Dependencies.Kotlin.Serialization.serialization)
                api(Dependencies.Kotlin.Coroutines.core)
            }
        }

        androidMain {}

        iosMain {}

    }
}

android {
    namespace = "com.gevorg89.rickandmorty.character.api"
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