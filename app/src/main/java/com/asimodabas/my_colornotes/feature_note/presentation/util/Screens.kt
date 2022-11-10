package com.asimodabas.my_colornotes.feature_note.presentation.util

sealed class Screens(val route: String) {
    object NoteScreen : Screens("note_screen")
    object AddEditNoteScreen : Screens("add_edit_note_screen")
}
