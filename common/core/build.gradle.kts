plugins {
    id("multiplatform-setup")
    id("android-setup").apply(false)
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
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

                api(Dependencies.Kamel.kamel)
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

sqldelight {
    database("AppDatabase") {
        packageName = "core.database"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    namespace = "com.gevorg89.rickandmorty.core"
}