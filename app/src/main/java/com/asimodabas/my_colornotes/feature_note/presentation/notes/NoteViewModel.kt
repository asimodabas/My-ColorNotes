package com.asimodabas.my_colornotes.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.asimodabas.my_colornotes.feature_note.domain.use_case.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel
@Inject constructor(
    private val noteUseCase:NoteUseCase
):ViewModel(){

    private val myState = mutableStateOf(NoteState())
    val state : State<NoteState> = myState

    fun onEvent(event:NoteEvent){
        when(event){
            is NoteEvent.Order ->{

            }
            is NoteEvent.deleteNotes ->{

            }
            is NoteEvent.ToggleOrderSection->{

            }
        }
    }
}
