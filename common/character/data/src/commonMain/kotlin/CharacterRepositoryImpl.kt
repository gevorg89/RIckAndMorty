import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import ktor.KtorCharacterDataSource
import sqldelight.KtorSqlDelightDataSource

class CharacterRepositoryImpl(
    private val remoteDataSource: KtorCharacterDataSource,
    private val localDataSource: KtorSqlDelightDataSource,

    ) : CharacterRepository {

    private var limit = 20L
    private var page: Int = 1
    private val offset: Long
        get() = (page - 1) * limit

    private var useLocal = false


    private val _characters: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())
    override val characters = _characters

    override suspend fun fetch(): Unit = withContext(Dispatchers.IO) {
        runCatching {
            if (page == 1) {
                val firstRemote = remoteDataSource.fetchFirst()
                val firstLocal = localDataSource.selectFirst()
                if (firstLocal == null) {
                    fetchAndSave(page)
                } else if (firstLocal.name != firstRemote.name) {
                    fetchAndSave(page)
                }
            } else {
                val sizeLocal = localDataSource.selectCount()
                useLocal = sizeLocal > offset
                if (!useLocal) {
                    fetchAndSave(page)
                }
            }
        }
        _characters.update {
            localDataSource.selectCharacters(limit, offset).map {
                Character(it.name.orEmpty(), it.image.orEmpty())
            }
        }
        page++
    }

    override suspend fun invalidate() {
        localDataSource.removeAllCharacters()
        page = 1
    }

    private suspend fun fetchAndSave(page: Int) {
        val items = remoteDataSource.fetch(page)
        items.forEach { character ->
            localDataSource.insertCharacter(character.name, character.image)
        }
    }
}