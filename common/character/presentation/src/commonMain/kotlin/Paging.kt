class Paging(private val characterRepository: CharacterRepository) {

    private var limit = 20L
    private var page: Int = 1
    private val offset: Long
        get() = (page - 1) * limit

    private var useLocal = false

    suspend fun fetch() {
        runCatching {
            if (page == 1) {
                val firstRemote = characterRepository.fetchFirstRemote()
                val firstLocal = characterRepository.selectFirst()
                if (firstLocal == null) {
                    characterRepository.fetch(page)
                } else if (firstLocal.name != firstRemote.name) {
                    characterRepository.fetch(page)
                }
            } else {
                val sizeLocal = characterRepository.selectCount()
                useLocal = sizeLocal > offset
                if (!useLocal) {
                    characterRepository.fetch(page)
                }
            }
        }
        characterRepository.getCharacters(limit, offset)
        page++
    }

    suspend fun invalidate() {
        characterRepository.invalidate()
        page = 1
    }

}