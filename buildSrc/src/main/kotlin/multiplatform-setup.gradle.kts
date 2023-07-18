plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("kapt")
//    id("org.jetbrains.compose")
}

kotlin {
    android()
    ios()
}