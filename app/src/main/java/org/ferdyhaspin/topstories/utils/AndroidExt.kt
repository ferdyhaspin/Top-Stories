package org.ferdyhaspin.topstories.utils

import android.content.Context
import android.text.format.DateFormat
import android.widget.Toast
import com.google.gson.Gson
import java.util.*

fun Context.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

inline fun <reified T> T.toJson(): String = Gson().toJson(this)

fun Long.getDate(): String {
    val calendar = Calendar.getInstance(Locale.ENGLISH)
    calendar.timeInMillis = this * 1000L
    return DateFormat.format("dd-MM-yyyy", calendar).toString()
}
