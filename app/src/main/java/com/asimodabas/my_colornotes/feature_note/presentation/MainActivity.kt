package com.asimodabas.my_colornotes.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.asimodabas.my_colornotes.ui.theme.MyColorNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyColorNotesTheme {


            }
        }
    }
}
