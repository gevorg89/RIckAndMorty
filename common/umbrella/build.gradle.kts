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
        androidMain {
            dependencies {
                api(Dependencies.Compose.activity)
                api(Dependencies.Compose.foundation)
                api(Dependencies.Compose.material)

                api(Dependencies.Kodein.android)
            }
        }
    }
}

android {
    namespace = "com.gevorg89.rickandmorty.umbrella"
}