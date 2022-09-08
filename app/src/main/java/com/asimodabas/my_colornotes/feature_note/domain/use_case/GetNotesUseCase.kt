package com.asimodabas.my_colornotes.feature_note.domain.use_case

import com.asimodabas.my_colornotes.feature_note.domain.model.Notes
import com.asimodabas.my_colornotes.feature_note.domain.repository.NotesRepository
import com.asimodabas.my_colornotes.feature_note.domain.util.NotesOrder
import com.asimodabas.my_colornotes.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NotesRepository
) {

    operator fun invoke(
        noteOrder: NotesOrder = NotesOrder.Date(OrderType.Descending)
    ): Flow<List<Notes>> {
        return repository.getNote().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NotesOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NotesOrder.Date -> notes.sortedBy { it.timestamp }
                        is NotesOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NotesOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NotesOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NotesOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}