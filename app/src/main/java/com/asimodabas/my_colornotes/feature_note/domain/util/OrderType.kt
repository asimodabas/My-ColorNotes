package com.asimodabas.my_colornotes.feature_note.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
