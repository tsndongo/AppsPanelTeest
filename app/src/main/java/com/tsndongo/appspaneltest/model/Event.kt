package com.tsndongo.appspaneltest.model

import com.google.gson.annotations.SerializedName
import java.util.*


data class Event(
    @SerializedName("picture_url")
    val image: String?,
    @SerializedName("published_at")
    val date: String,
    val title: String,
    val description: String
)

fun ArrayList<Event>.sortByDate(): ArrayList<Event>{
    this.sortByDescending { it.date }
    return this
}
