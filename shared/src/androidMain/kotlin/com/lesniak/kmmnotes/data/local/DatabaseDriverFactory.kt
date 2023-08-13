package com.lesniak.kmmnotes.data.local

import android.content.Context
import com.lesniak.kmmnotes.db.NotesDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {

    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(NotesDatabase.Schema, context, "notes.db")
}