package com.asimodabas.my_colornotes.feature_note.domain.repository

import com.asimodabas.my_colornotes.feature_note.domain.model.Notes
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNote(): Flow<List<Notes>>

    suspend fun getNotesById(id: Int): Notes?

    suspend fun insertNotes(note: Notes)

    suspend fun deleteNotes(note: Notes)
}