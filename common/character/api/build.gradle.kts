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
    }
}

android {
    namespace = "com.gevorg89.rickandmorty.character.api"
}