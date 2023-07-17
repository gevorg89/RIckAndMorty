package com.gevorg89.rickandmorty

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform