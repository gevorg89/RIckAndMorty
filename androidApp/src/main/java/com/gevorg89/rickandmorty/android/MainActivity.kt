package com.gevorg89.rickandmorty.android

import AndroidApp
import PlatformSDK
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import decompose.DefaultRootComponent
import platform.PlatformConfiguration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PlatformSDK.init(PlatformConfiguration(this))
        val root = DefaultRootComponent(defaultComponentContext())
        setContent {
            MyApplicationTheme {
                AndroidApp(root)
            }
        }
    }

}