package com.asimodabas.my_colornotes.di

import android.app.Application
import androidx.room.Room
import com.asimodabas.my_colornotes.feature_note.data.data_source.NotesDatabase
import com.asimodabas.my_colornotes.feature_note.data.repository.NotesRepositoryImp
import com.asimodabas.my_colornotes.feature_note.domain.repository.NotesRepository
import com.asimodabas.my_colornotes.feature_note.domain.use_case.AddNotes
import com.asimodabas.my_colornotes.feature_note.domain.use_case.DeleteNotesUseCase
import com.asimodabas.my_colornotes.feature_note.domain.use_case.GetNoteUseCase
import com.asimodabas.my_colornotes.feature_note.domain.use_case.GetNotesUseCase
import com.asimodabas.my_colornotes.feature_note.domain.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    fun provideNotesDatabase(app: Application): NotesDatabase {
        return Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNotesRepository(db: NotesDatabase): NotesRepository {
        return NotesRepositoryImp(db.notesDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NotesRepository): NoteUseCase {
        return NoteUseCase(
            getNotes = GetNotesUseCase(repository),
            deleteNotes = DeleteNotesUseCase(repository),
            addNote = AddNotes(repository),
            getNote = GetNoteUseCase(repository)
        )
    }
}