plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    ios()
//    ios {
//        binaries {
//            framework {
//                baseName = "shared"
//                isStatic = true
//            }
//        }
//    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {

            baseName = "shared"
            //isStatic = true
            //binaryOption("bundleId", "com.example.common.umbrella")
            println("SDFsds " + this.baseName)
        }
    }

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
    }
}