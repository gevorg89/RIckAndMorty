plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    ios()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {

            baseName = "shared"
            isStatic = true
            binaryOption("bundleId", "com.example.common.umbrella")
            export("com.arkivanov.decompose:decompose:2.0.0")
        }
    }

/*    targets
        .filterIsInstance<KotlinNativeTarget>()
        .filter { it.konanTarget.family == Family.IOS }
        .forEach {
            it.binaries.framework {

                export("com.arkivanov.decompose:decompose:2.0.0")
//                export("com.arkivanov.essenty:lifecycle:<essenty_version>")

                // Optional, only if you need state preservation on Darwin (Apple) targets
//                export("com.arkivanov.essenty:state-keeper:<essenty_version>")

                // Optional, only if you need state preservation on Darwin (Apple) targets
//                export("com.arkivanov.parcelize.darwin:runtime:<parcelize_darwin_version>")
            }
        }*/

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                api(Dependencies.Decompose.decompose)
                api(Dependencies.Decompose.extension)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {

            }
        }
    }
}