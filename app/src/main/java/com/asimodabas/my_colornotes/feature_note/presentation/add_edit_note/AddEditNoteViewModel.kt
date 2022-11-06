package com.asimodabas.my_colornotes.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.asimodabas.my_colornotes.feature_note.domain.model.Notes
import com.asimodabas.my_colornotes.feature_note.domain.use_case.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCase
) : ViewModel() {

    private val myNoteTitle = mutableStateOf(NoteTextFieldState(nHint = "Enter title..."))
    val noteTitle: State<NoteTextFieldState> = myNoteTitle

    private val myNoteContent = mutableStateOf(NoteTextFieldState(nHint = "Enter some content"))
    val noteContent: State<NoteTextFieldState> = myNoteContent

    private val myNoteColor = mutableStateOf(Notes.noteColors.random().toArgb())
    val noteColor: State<Int> = myNoteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddEditNoteEvent) {

    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }
}