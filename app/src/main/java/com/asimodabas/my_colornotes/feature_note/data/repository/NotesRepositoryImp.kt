package com.asimodabas.my_colornotes.feature_note.data.repository

import com.asimodabas.my_colornotes.feature_note.data.data_source.NotesDao
import com.asimodabas.my_colornotes.feature_note.domain.model.Notes
import com.asimodabas.my_colornotes.feature_note.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImp(private val dao: NotesDao) : NotesRepository {

    override fun getNote(): Flow<List<Notes>> {
        return dao.getNote()
    }

    override suspend fun getNotesById(id: Int): Notes? {
        return dao.getNotesById(id)
    }

    override suspend fun insertNotes(note: Notes) {
        dao.insertNotes(note)
    }

    override suspend fun deleteNotes(note: Notes) {
        dao.deleteNotes(note)
    }
}