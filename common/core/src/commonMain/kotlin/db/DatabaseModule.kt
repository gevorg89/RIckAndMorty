package db

import core.database.AppDatabase
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

internal val dataBaseModule = DI.Module("dataBaseModule") {
    bindSingleton {
        DbDriverFactory(instance()).createDriver(AppDatabase.Schema, "rickandmorty.db")
    }
}