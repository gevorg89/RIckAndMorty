import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun fetch(page: Int)
    suspend fun invalidate()
    suspend fun fetchFirstRemote() : Character
    suspend fun selectFirst() : Character?
    suspend fun selectCount() : Long
    suspend fun getCharacters(limit: Long, offset: Long)
    val characters : Flow<List<Character>>
}