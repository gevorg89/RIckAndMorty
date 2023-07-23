interface CharacterRepository {
    suspend fun fetch(page: Int): List<Character>
    suspend fun invalidate()
}