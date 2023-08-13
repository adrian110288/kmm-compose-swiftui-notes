package com.lesniak.kmmnotes.domain.notes

import com.lesniak.kmmnotes.presentation.*
import kotlinx.datetime.*

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val createdAt: LocalDateTime
) {

    companion object {
        private val colors = listOf(
            RedOrangeHex,
            RedPinkHex,
            BabyBlueHex,
            VioletHex,
            LightGreenHex
        )

        fun randomColor() = colors.random()
    }
}
