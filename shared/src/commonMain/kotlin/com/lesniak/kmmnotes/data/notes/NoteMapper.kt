package com.lesniak.kmmnotes.data.notes

import com.lesniak.kmmnotes.domain.notes.Note
import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        colorHex = colorHex,
        createdAt = Instant.fromEpochMilliseconds(created_at)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}