interface CharacterRepository {
    suspend fun fetch() : List<Character>
}