package com.asimodabas.my_colornotes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asimodabas.my_colornotes.ui.theme.CustomBlue
import com.asimodabas.my_colornotes.ui.theme.CustomGreen
import com.asimodabas.my_colornotes.ui.theme.CustomOrange
import com.asimodabas.my_colornotes.ui.theme.CustomPink
import com.asimodabas.my_colornotes.ui.theme.CustomViolet

@Entity
data class Notes(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors =
            listOf(
                CustomOrange,
                CustomGreen,
                CustomViolet,
                CustomBlue,
                CustomPink
            )
    }
}

class NoteException(message: String) : Exception(message)