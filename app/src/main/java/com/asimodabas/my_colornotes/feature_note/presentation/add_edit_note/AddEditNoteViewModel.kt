package com.asimodabas.my_colornotes.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asimodabas.my_colornotes.feature_note.domain.model.NoteException
import com.asimodabas.my_colornotes.feature_note.domain.model.Notes
import com.asimodabas.my_colornotes.feature_note.domain.use_case.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCase,
    savedStateHandle : SavedStateHandle
) : ViewModel() {

    private val myNoteTitle = mutableStateOf(NoteTextFieldState(nHint = "Enter title..."))
    val noteTitle: State<NoteTextFieldState> = myNoteTitle

    private val myNoteContent = mutableStateOf(NoteTextFieldState(nHint = "Enter some content"))
    val noteContent: State<NoteTextFieldState> = myNoteContent

    private val myNoteColor = mutableStateOf(Notes.noteColors.random().toArgb())
    val noteColor: State<Int> = myNoteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentMyNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        currentMyNoteId = note.id
                        myNoteTitle.value = noteTitle.value.copy(
                            nText = note.title,
                            isHintVisible = false
                        )
                        myNoteContent.value = myNoteContent.value.copy(
                            nText = note.content,
                            isHintVisible = false
                        )
                        myNoteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                myNoteTitle.value = noteTitle.value.copy(
                    nText = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                myNoteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.nText.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                myNoteContent.value = myNoteContent.value.copy(
                    nText = event.value
                )
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                myNoteContent.value = myNoteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            myNoteContent.value.nText.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor -> {
                myNoteColor.value = event.color
            }
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Notes(
                                title = noteTitle.value.nText,
                                content = noteContent.value.nText,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentMyNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: NoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }
}