package sqldelight

import core.database.AppDatabase
import database.Character

class KtorSqlDelightDataSource(database: AppDatabase) {

    private val dbQuery = database.characterQueries

    internal fun removeAllCharacters() {
        dbQuery.transaction {
            dbQuery.removeAllCharacters()
        }
    }

    internal fun insertCharacter(name : String, image: String) {
        dbQuery.transaction {
            dbQuery.insertCharacter(name, image)
        }
    }

    internal fun selectAllCharacters(): List<Character> {
        return dbQuery.selectAllCharacters().executeAsList()
    }
}