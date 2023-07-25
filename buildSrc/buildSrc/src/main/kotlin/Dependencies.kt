object Dependencies {

    object Kodein {
        private const val version = "7.20.2"
        const val core = "org.kodein.di:kodein-di:$version"
        const val android = "org.kodein.di:kodein-di-framework-android-x:$version"
    }

    object Kotlin {
        private const val version = "1.8.20"
        const val gradlePlugin =  "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Serialization {
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:1.5.31"
            const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.1"
        }

        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2"
        }
    }

    object Compose {
        const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:1.4.0"
        const val activity = "androidx.activity:activity-compose:1.7.2"

        private const val uiVersion = "1.4.3"
        const val foundation = "androidx.compose.foundation:foundation:$uiVersion"
        const val material = "androidx.compose.material:material:$uiVersion"
    }

    object Ktor {
        private const val version = "2.3.2"
        const val core = "io.ktor:ktor-client-content-negotiation:$version"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val json = "io.ktor:ktor-client-json:$version"
        const val ios = "io.ktor:ktor-client-darwin:$version"
        const val logging = "io.ktor:ktor-client-logging:$version"
        const val android = "io.ktor:ktor-client-android:$version"
    }

    object SqlDelight {
        private const val version = "1.5.5"
        const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$version"
        const val core = "com.squareup.sqldelight:runtime:$version"
        const val android = "com.squareup.sqldelight:android-driver:$version"
        const val ios = "com.squareup.sqldelight:native-driver:$version"
    }

    object Android {
        const val gradlePlugin = "com.android.tools.build:gradle:8.0.2"
    }

    //Image loader
    object Kamel {
        const val kamel = "media.kamel:kamel-image:0.7.0"
    }

    object Logging {
        const val lgging = "org.lighthousegames:logging:1.3.0"
    }

    object Decompose {
        private const val version = "2.0.0-compose-experimental"
        const val decompose = "com.arkivanov.decompose:decompose:$version"
        const val extension = "com.arkivanov.decompose:extensions-compose-jetbrains:$version"

        private const val versionEssenty = "1.1.0"
        const val essentyLifecycle = "com.arkivanov.essenty:lifecycle:$versionEssenty"
        const val essentyStateKeeper = "com.arkivanov.essenty:state-keeper:$versionEssenty"
    }

}