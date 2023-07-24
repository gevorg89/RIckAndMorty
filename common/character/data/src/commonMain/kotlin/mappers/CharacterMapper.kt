package mappers

import Character
import Results

fun Results.toDomain(): Character {
    return Character(
        id = requireNotNull(id).toLong(),
        name = name.orEmpty(),
        image = image.orEmpty()
    )
}

fun database.Character.toDomain(): Character {
    return Character(
        id = id,
        name = name.orEmpty(),
        image = image.orEmpty()
    )
}