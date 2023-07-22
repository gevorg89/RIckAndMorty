import ktor.KtorCharacterDataSource
import sqldelight.KtorSqlDelightDataSource

class CharacterRepositoryImpl(
    private val remoteDataSource: KtorCharacterDataSource,
    private val localDataSource: KtorSqlDelightDataSource,
) : CharacterRepository {
    override suspend fun fetch(page: Int): List<Character> {
        return remoteDataSource.fetch(page)
    }
}