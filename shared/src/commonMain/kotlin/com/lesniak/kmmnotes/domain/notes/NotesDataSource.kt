package com.lesniak.kmmnotes.domain.notes

interface NotesDataSource {
    suspend fun getNotes(): List<Note>
    suspend fun getNoteById(noteId: Long): Note?
    suspend fun addOrUpdateNote(note: Note)
    suspend fun removeNote(noteId: Long)
}