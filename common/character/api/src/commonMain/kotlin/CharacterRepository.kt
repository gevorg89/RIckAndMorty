import models.Character

interface CharacterRepository {
    suspend fun fetch() : List<Character>
}