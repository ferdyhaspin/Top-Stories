package org.ferdyhaspin.topstories.utils

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson

fun String.removeSpacing(): String = trim().replace(" ", "")

fun String.addColor(hexColor: String): String = "<span style='color:$hexColor'>$this</span>"

fun String?.notNull(): String = this?.replace("\n", "") ?: ""

fun Context.getStringArray(id: Int): Array<String> {
    return resources?.getStringArray(id) ?: arrayOf("")
}

fun Context.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

inline fun <reified T> T.toJson(): String = Gson().toJson(this)

inline fun <reified T> String.toObject(): T = Gson().fromJson(this, T::class.java)

