package com.tsndongo.appspaneltest.extension

import android.text.format.DateUtils
import android.util.Patterns
import java.util.*


fun String.formatDate(): String {
    val cal = Calendar.getInstance()
    cal.timeInMillis = Date(this.toLong() * 1000).time
    val today = Calendar.getInstance().timeInMillis
    val delta = today - cal.timeInMillis
    val deltaDays = (delta.toFloat() / (24 * 60 * 60 * 1000)).toInt()
    return when {
        DateUtils.isToday(cal.timeInMillis) -> "aujourd'hui"
        deltaDays == 1 -> "hier"
        else -> "il y a $deltaDays jour(s)"
    }
}

fun String.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()