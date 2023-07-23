import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun fetch()
    suspend fun invalidate()
    val characters : Flow<List<Character>>
}