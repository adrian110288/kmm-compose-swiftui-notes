package com.lesniak.kmmnotes.data.local

import com.lesniak.kmmnotes.db.NotesDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory() {

    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(NotesDatabase.Schema, "notes.db")

}