package ktor

import Character
import CharacterRemote
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import mappers.toDomain

class KtorCharacterDataSource (private val httpClient: HttpClient) {
    suspend fun fetch() : List<Character> {
        return runCatching {
            val character = httpClient.get<CharacterRemote> {
                url {
                    encodedPath = "api/character"
                }
            }
            character.results.map { it.toDomain() }
        }.onFailure {
            emptyList<Character>()
        }.getOrThrow()
    }
}