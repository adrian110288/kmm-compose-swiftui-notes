package com.lesniak.kmmnotes.data.local

import com.lesniak.kmmnotes.data.notes.SqlDelightNotesDataSource
import com.lesniak.kmmnotes.db.NotesDatabase

class DatabaseModule {

    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource by lazy { SqlDelightNotesDataSource(NotesDatabase(factory.createDriver())) }
}