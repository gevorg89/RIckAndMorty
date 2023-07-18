plugins {
    kotlin("multiplatform")
    id("multiplatform-compose-setup")
    id("android-setup").apply(false)
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