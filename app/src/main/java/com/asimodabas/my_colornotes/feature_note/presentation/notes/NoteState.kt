package com.asimodabas.my_colornotes.feature_note.presentation.notes

import com.asimodabas.my_colornotes.feature_note.domain.model.Notes
import com.asimodabas.my_colornotes.feature_note.domain.util.NotesOrder
import com.asimodabas.my_colornotes.feature_note.domain.util.OrderType

data class NoteState(
    val note :List<Notes> = emptyList(),
    val notesOrder :NotesOrder = NotesOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean = false
)