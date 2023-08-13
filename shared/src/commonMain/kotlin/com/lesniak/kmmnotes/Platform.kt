package com.lesniak.kmmnotes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform