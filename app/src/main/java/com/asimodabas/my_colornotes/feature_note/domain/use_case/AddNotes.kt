package com.asimodabas.my_colornotes.feature_note.domain.use_case

import com.asimodabas.my_colornotes.feature_note.domain.model.NoteException
import com.asimodabas.my_colornotes.feature_note.domain.model.Notes
import com.asimodabas.my_colornotes.feature_note.domain.repository.NotesRepository

class AddNotes(
    private val repository: NotesRepository
) {

    @Throws(NoteException::class)
    suspend operator fun invoke(note: Notes) {
        if(note.title.isBlank()) {
            throw NoteException("Please fill in the title.")
        }
        if(note.content.isBlank()) {
            throw NoteException("Please fill in the content.")
        }
        repository.insertNotes(note)
    }
}