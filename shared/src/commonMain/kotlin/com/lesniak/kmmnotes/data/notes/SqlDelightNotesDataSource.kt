package com.lesniak.kmmnotes.data.notes

import com.lesniak.kmmnotes.db.NotesDatabase
import com.lesniak.kmmnotes.domain.notes.Note
import com.lesniak.kmmnotes.domain.notes.NotesDataSource
import com.lesniak.kmmnotes.domain.time.toEpochMilli

class SqlDelightNotesDataSource(private val db: NotesDatabase) : NotesDataSource {

    private val queries = db.notesQueries

    override suspend fun getNotes(): List<Note> =
        queries.getAllNotes()
            .executeAsList()
            .map { it.toNote() }


    override suspend fun getNoteById(noteId: Long): Note? =
        queries.getNoteById(noteId).executeAsOneOrNull()?.toNote()

    override suspend fun addOrUpdateNote(note: Note) =
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            created_at = toEpochMilli(note.createdAt),
        )

    override suspend fun removeNote(noteId: Long) =
        queries.deleteNote(noteId)
}