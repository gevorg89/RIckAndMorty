package ktor

import Character
import CharacterRemote
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import mappers.toDomain

class KtorCharacterDataSource(private val httpClient: HttpClient) {

    suspend fun fetch(page: Int, count: Int = 20): List<Character> {
        return runCatching {
            val character =
                httpClient.get("/api/character/?page=$page&count=$count").body<CharacterRemote>()
            character.results.map { it.toDomain() }
        }.onFailure {
            emptyList<Character>()
        }.getOrThrow()
    }

    suspend fun fetchFirst(): Character {
        return fetch(1, 1).first()
    }
}