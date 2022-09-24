package com.asimodabas.my_colornotes.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.asimodabas.my_colornotes.feature_note.domain.util.NotesOrder
import com.asimodabas.my_colornotes.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier,
    notesOrder: NotesOrder = NotesOrder.Date(OrderType.Descending),
    onOrderChange:(NotesOrder) -> Unit
){
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            NoteRadioButton(
                text = "Tittle",
                checked = notesOrder is NotesOrder.Title,
                onCheck = {
                    onOrderChange(
                        NotesOrder.Title(notesOrder.orderType))})
            Spacer(modifier = Modifier.width(5.dp))
            NoteRadioButton(
                text = "Date",
                checked = notesOrder is NotesOrder.Date,
                onCheck = {
                    onOrderChange(
                        NotesOrder.Date(notesOrder.orderType))})
            Spacer(modifier = Modifier.width(5.dp))
            NoteRadioButton(
                text = "Color",
                checked = notesOrder is NotesOrder.Color,
                onCheck = {
                    onOrderChange(
                        NotesOrder.Color(notesOrder.orderType))})
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            NoteRadioButton(
                text = "Ascending",
                checked = notesOrder.orderType is OrderType.Ascending,
                onCheck = {
                    onOrderChange(
                        notesOrder.copy(OrderType.Ascending))})
            Spacer(modifier = Modifier.width(5.dp))
            NoteRadioButton(
                text = "Descending",
                checked = notesOrder.orderType is OrderType.Descending,
                onCheck = {
                    onOrderChange(
                        notesOrder.copy(OrderType.Descending))})
        }
    }
}