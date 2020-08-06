package com.tsndongo.appspaneltest.model

import android.text.format.DateUtils
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
    this.sortBy { it.date }
    return this
}

fun String.formatDate(): String {
    val cal = Calendar.getInstance()
    cal.timeInMillis = Date(this.toLong() * 1000).time
    val today = Calendar.getInstance().timeInMillis
    val delta = today - cal.timeInMillis
    val deltaDays = (delta.toFloat() / (24 * 60 * 60 * 1000)).toInt()
    return when {
        DateUtils.isToday(cal.timeInMillis) -> "aujourd'hui"
        deltaDays == 1 -> "hier"
        else -> "il y a ${deltaDays} jour(s)"
    }
}