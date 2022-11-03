package com.asimodabas.my_colornotes.feature_note.domain.use_case

data class NoteUseCase(
    val getNotes: GetNotesUseCase,
    val deleteNotes: DeleteNotesUseCase,
    val getNote: GetNoteUseCase,
    val addNote: AddNotes
)
