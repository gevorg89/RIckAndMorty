package com.gevorg89.rickandmorty.android

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class AndroidApp : Application(), DIAware {
    override val di by DI.lazy {
        import(androidXModule(this@AndroidApp))
    }
}