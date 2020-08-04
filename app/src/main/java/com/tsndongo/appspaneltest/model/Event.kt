package com.tsndongo.appspaneltest.model

import java.util.Date

data class Event(
    val image: String,
    val date: String,
    val title: String,
    val description: String
)

fun ArrayList<Event>.sortByDate(): ArrayList<Event>{
    this.sortBy { it.date }
    return this
}