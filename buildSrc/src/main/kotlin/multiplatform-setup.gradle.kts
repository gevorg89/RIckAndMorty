plugins {
    id("com.android.library")
    kotlin("multiplatform")
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
        }
    }
//
//    sourceSets {
//        val commonMain by getting {
//            dependencies {
//                dependencies {
//                    implementation(Dependencies.Kodein.core)
//                }
//            }
//        }
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
//        val iosMain by getting {
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//
//            dependencies {
//
//            }
//        }
//    }
}