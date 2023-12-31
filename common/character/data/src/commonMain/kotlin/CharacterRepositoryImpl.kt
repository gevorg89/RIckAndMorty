import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import ktor.KtorCharacterDataSource
import mappers.toDomain
import sqldelight.KtorSqlDelightDataSource

class CharacterRepositoryImpl(
    private val remoteDataSource: KtorCharacterDataSource,
    private val localDataSource: KtorSqlDelightDataSource,

    ) : CharacterRepository {

    override suspend fun fetch(page: Int): Unit = withContext(Dispatchers.IO) {
        runCatching {
            fetchAndSave(page)
        }
    }

    override suspend fun getCharacter(id: Long): Character {
        return localDataSource.selectCharacter(id).toDomain()
    }

    override suspend fun getCharacters(limit: Long, offset: Long): List<Character> {
        return localDataSource.selectCharacters(limit, offset).map {
            Character(it.id, it.name.orEmpty(), it.image.orEmpty())
        }
    }

    override suspend fun fetchFirstRemote(): Character {
        return remoteDataSource.fetchFirst()
    }

    override suspend fun selectFirst(): Character? {
        return localDataSource.selectFirst()?.toDomain()
    }

    override suspend fun selectCount(): Long {
        return localDataSource.selectCount()
    }

    override suspend fun invalidate() {
        localDataSource.removeAllCharacters()
    }

    private suspend fun fetchAndSave(page: Int) {
        val items = remoteDataSource.fetch(page)
        items.forEach { character ->
            localDataSource.insertCharacter(character.id, character.name, character.image)
        }
    }
}