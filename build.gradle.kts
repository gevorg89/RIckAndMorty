import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
}

 subprojects {
     project.plugins.applyBaseConfig(project)
 }

 buildscript {
     dependencies {
         // Use the same version in the error
         classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.17.3")
     }
 }

 allprojects {
     apply(plugin = "kotlinx-atomicfu")
 }

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

 fun BaseExtension.baseConfig() {

     compileSdkVersion(33)

     defaultConfig.apply {
         minSdk = 24
         targetSdk = 33
     }

     compileOptions.apply {
         sourceCompatibility = JavaVersion.VERSION_17
         targetCompatibility = JavaVersion.VERSION_17
     }

     tasks.withType<KotlinCompile> {
         kotlinOptions {
             jvmTarget = "1.8"
         }
     }
 }

fun PluginContainer.applyBaseConfig(project: Project) {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> {
                project.extensions
                    .getByType<AppExtension>()
                    .apply {
                        baseConfig()
                    }
            }
            is LibraryPlugin -> {
                project.extensions
                    .getByType<LibraryExtension>()
                    .apply {
                        baseConfig()
                    }
            }
        }
    }
}
