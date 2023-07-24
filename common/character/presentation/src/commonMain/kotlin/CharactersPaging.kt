import paging.Paging

class CharactersPaging(private val characterRepository: CharacterRepository) : Paging<Character>() {

    override suspend fun fetch(page: Int) {
        characterRepository.fetch(page)
    }

    override suspend fun checkForFetch(): Boolean {
        val firstRemote = characterRepository.fetchFirstRemote()
        val firstLocal = characterRepository.selectFirst()
        if (firstLocal == null) {
            return true
        } else if (firstLocal.name != firstRemote.name) {
            return true
        }
        return false
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