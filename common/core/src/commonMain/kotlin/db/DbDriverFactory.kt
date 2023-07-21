package db

import com.squareup.sqldelight.db.SqlDriver
import platform.PlatformConfiguration

expect class DbDriverFactory constructor(platformConfiguration: PlatformConfiguration) {
    fun createDriver(schema: SqlDriver.Schema, name: String) : SqlDriver
}