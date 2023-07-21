package db

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

internal val dataBaseModule = DI.Module("dataBaseModule") {
    bind<DbDriverFactory>() with singleton {
        DbDriverFactory(instance())
    }
}