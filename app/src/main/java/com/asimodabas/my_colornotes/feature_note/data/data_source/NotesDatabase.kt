package com.asimodabas.my_colornotes.feature_note.data.data_source

import NotesDao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.asimodabas.my_colornotes.feature_note.domain.model.Notes

@Database(
    entities = [Notes::class],
    version = 1
)
abstract class NotesDatabase: RoomDatabase() {

    abstract val notesDao: NotesDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}