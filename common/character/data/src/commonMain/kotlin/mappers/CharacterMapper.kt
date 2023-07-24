package mappers

import Character
import Results

fun Results.toDomain() : Character {
    return Character(name = name.orEmpty(), image = image.orEmpty())
}

fun database.Character.toDomain(): Character {
    return Character(name = name.orEmpty(), image = image.orEmpty())
}