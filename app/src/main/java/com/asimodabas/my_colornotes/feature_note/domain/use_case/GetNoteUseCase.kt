package com.asimodabas.my_colornotes.feature_note.domain.use_case

import com.asimodabas.my_colornotes.feature_note.domain.model.Notes
import com.asimodabas.my_colornotes.feature_note.domain.repository.NotesRepository

class GetNoteUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(id: Int): Notes? {
        return repository.getNotesById(id)
    }
}