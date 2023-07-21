package com.gevorg89.rickandmorty.android

import AndroidApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.kodein.di.DI
import org.kodein.di.DIAware

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                AndroidApp()
            }
        }
    }

//    override val di: DI by di()
}