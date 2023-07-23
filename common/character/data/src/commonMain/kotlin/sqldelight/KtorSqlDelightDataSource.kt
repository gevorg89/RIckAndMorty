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

    internal fun insertCharacter(name: String, image: String) {
        dbQuery.transaction {
            dbQuery.insertCharacter(name, image)
        }
    }

    internal fun selectAllCharacters(): List<Character> {
        return dbQuery.selectAllCharacters().executeAsList()
    }

    internal fun selectCharacters(limit: Long = 20, offset: Long): List<Character> {
        return dbQuery.selectCharacters(limit, offset).executeAsList()
    }

    internal fun selectCount(): Long {
        return dbQuery.selectCount().executeAsOne()
    }

    internal fun selectFirst(): Character? {
        return dbQuery.selectFirstCharacter().executeAsOneOrNull()
    }
}