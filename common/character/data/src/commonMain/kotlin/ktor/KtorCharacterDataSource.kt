package ktor

import Character
import CharacterRemote
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import mappers.toDomain

class KtorCharacterDataSource(private val httpClient: HttpClient) {
    suspend fun fetch(): List<Character> {
        return runCatching {
            val character = httpClient.get("/api/character").body<CharacterRemote>()
            character.results.map { it.toDomain() }
        }.onFailure {
            emptyList<Character>()
        }.getOrThrow()
    }
}