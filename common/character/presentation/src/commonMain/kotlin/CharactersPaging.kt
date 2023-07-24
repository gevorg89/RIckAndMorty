import paging.Paging

class CharactersPaging(private val characterRepository: CharacterRepository) : Paging<Character>() {

    override suspend fun fetch(page: Int) {
        characterRepository.fetch(page)
    }

    override suspend fun checkForFetch(): Boolean {
        return runCatching {
            val firstRemote = characterRepository.fetchFirstRemote()
            val firstLocal = characterRepository.selectFirst()
            firstLocal == null || firstLocal.name != firstRemote.name
        }.getOrDefault(false)
    }

    override suspend fun getLocalCount(): Long {
        return characterRepository.selectCount()
    }

    override suspend fun getLocal(limit: Long, offset: Long): List<Character> {
        return characterRepository.getCharacters(limit, offset)
    }

    override suspend fun invalidateRepository() {
        characterRepository.invalidate()
        invalidate()
    }

}