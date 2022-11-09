package com.asimodabas.my_colornotes.feature_note.presentation.notes

import com.asimodabas.my_colornotes.feature_note.domain.model.Notes
import com.asimodabas.my_colornotes.feature_note.domain.util.NotesOrder

sealed class NoteEvent {
    data class Order(val noteOrder: NotesOrder) : NoteEvent()
    data class deleteNotes(val note: Notes) : NoteEvent()
    object RestorationNote : NoteEvent()
    object ToggleOrderSection : NoteEvent()
}