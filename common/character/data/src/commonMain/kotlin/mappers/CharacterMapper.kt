package mappers

import Character
import Results

fun Results.toDomain() : Character {
    return Character(name = name.orEmpty())
}