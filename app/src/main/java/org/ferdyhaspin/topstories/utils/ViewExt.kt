package org.ferdyhaspin.topstories.utils

import android.view.View


fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toGoneIf(isGone: Boolean) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}