package com.asimodabas.my_colornotes.feature_note.presentation.notes.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.asimodabas.my_colornotes.feature_note.domain.model.Notes

@Composable
fun NotesItem(
    note: Notes,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 15.dp,
    cutCornerRadius: Dp = 25.dp,
    onDelete: () -> Unit
) {
    Box(modifier = modifier) {
        Canvas(
            modifier =
            Modifier
                .matchParentSize()
        ) {
            val clpPath = Path().apply {
                lineTo(size.width - cutCornerRadius.toPx(), 0f)
                lineTo(size.width, cutCornerRadius.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            clipPath(clpPath) {
                drawRoundRect(
                    color = Color(note.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(ColorUtils.blendARGB(note.color, 0x000000, 0.2f)),
                    topLeft = Offset(size.width - cutCornerRadius.toPx(), -100f),
                    size = Size(cutCornerRadius.toPx() + 100f, cutCornerRadius.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(15.dp)
                .padding(end = 30.dp)
        )
        {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.secondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            onClick = onDelete,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Dote",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}