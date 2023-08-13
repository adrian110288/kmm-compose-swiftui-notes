package com.lesniak.kmmnotes.android.di

import android.app.Application
import com.lesniak.kmmnotes.data.local.DatabaseDriverFactory
import com.lesniak.kmmnotes.data.notes.SqlDelightNotesDataSource
import com.lesniak.kmmnotes.db.NotesDatabase
import com.lesniak.kmmnotes.domain.notes.NotesDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NotesDataSource {
        return SqlDelightNotesDataSource(NotesDatabase(driver))
    }
}