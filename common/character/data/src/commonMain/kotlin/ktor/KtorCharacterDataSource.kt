package ktor

import io.ktor.client.HttpClient
import models.Character

class KtorCharacterDataSource (val httpClient: HttpClient) {
    suspend fun fetch() : List<Character> {
        return emptyList()
    }
}