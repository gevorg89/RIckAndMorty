import ktor.KtorCharacterDataSource
import models.Character
import sqldelight.KtorSqlDelightDataSource

class CharacterRepositoryImpl(
    private val remoteDataSource: KtorCharacterDataSource,
    private val localDataSource: KtorSqlDelightDataSource,
) : CharacterRepository {
    override suspend fun fetch(): List<Character> {
        return remoteDataSource.fetch()
    }
}