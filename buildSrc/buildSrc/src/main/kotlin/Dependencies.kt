object Dependencies {


    object Kotlin {
        private const val version = "1.8.20"
        const val gradlePlugin =  "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

    }

    object Compose {
        private const val version = "1.4.0"
        const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$version"
    }

    object Android {
        const val gradlePlugin = "com.android.tools.build:gradle:8.0.2"
    }

}