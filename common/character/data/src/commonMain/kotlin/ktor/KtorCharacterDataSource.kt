package ktor

import Character
import CharacterRemote
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import mappers.toDomain

class KtorCharacterDataSource(private val httpClient: HttpClient) {
    suspend fun fetch(): List<Character> {
        return runCatching {
            val character = httpClient.get<CharacterRemote>("/api/character")
            character.results.map { it.toDomain() }
        }.onFailure {
            emptyList<Character>()
        }.getOrThrow()
    }
}