package com.lesniak.kmmnotes.domain.notes

class SearchNotes {

    operator fun invoke(notes: List<Note>, query: String): List<Note> =
        if (query.isEmpty())
            notes
        else {
            notes.filter { note ->
                note.title.trim().contains(query, ignoreCase = true) ||
                        note.content.trim().contains(query, ignoreCase = true)
            }.sortedByDescending { note ->
                note.createdAt
            }
        }
}