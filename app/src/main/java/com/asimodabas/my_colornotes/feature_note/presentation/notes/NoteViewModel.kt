package com.asimodabas.my_colornotes.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asimodabas.my_colornotes.feature_note.domain.model.Notes
import com.asimodabas.my_colornotes.feature_note.domain.use_case.NoteUseCase
import com.asimodabas.my_colornotes.feature_note.domain.util.NotesOrder
import com.asimodabas.my_colornotes.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel
@Inject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    private val myState = mutableStateOf(NoteState())
    private var recentlDeleteNote: Notes? = null
    val state: State<NoteState> = myState
    private var getNotJob: Job? = null

    private fun getNotes(noteOrder: NotesOrder) {
        getNotJob?.cancel()
        getNotJob = noteUseCase.getNotes(noteOrder)
            .onEach { note ->
                myState.value = state.value.copy(
                    notes = note,
                    notesOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

    init {
        getNotes(NotesOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.Order -> {
                if (state.value.notesOrder::class == event.noteOrder::class &&
                    state.value.notesOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }
            is NoteEvent.deleteNotes -> {
                viewModelScope.launch {
                    noteUseCase.deleteNotes(event.note)
                    recentlDeleteNote = event.note
                }
            }
            is NoteEvent.ToggleOrderSection -> {
                myState.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is NoteEvent.RestorationNote -> {
                viewModelScope.launch {
                    noteUseCase.addNote(recentlDeleteNote ?: return@launch)
                    recentlDeleteNote = null
                }
            }
        }
    }
}
