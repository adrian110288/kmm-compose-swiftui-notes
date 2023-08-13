package com.lesniak.kmmnotes.android.list

import com.lesniak.kmmnotes.domain.notes.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)