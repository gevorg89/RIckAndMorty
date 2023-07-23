import ktor.KtorCharacterDataSource
import sqldelight.KtorSqlDelightDataSource

class CharacterRepositoryImpl(
    private val remoteDataSource: KtorCharacterDataSource,
    private val localDataSource: KtorSqlDelightDataSource,
) : CharacterRepository {
    override suspend fun fetch(page: Int): List<Character> {
        val items = remoteDataSource.fetch(page)
        items.forEach {character->
            localDataSource.insertCharacter(character.name, character.image)
        }
        return localDataSource.selectAllCharacters().map {
            Character(it.name.orEmpty(), it.image.orEmpty())
        }
    }

    override suspend fun invalidate() {
        localDataSource.removeAllCharacters()
    }
}