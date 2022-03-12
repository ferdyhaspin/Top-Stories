package org.ferdyhaspin.topstories.utils

import android.view.View

fun View.toGoneIf(isGone: Boolean) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}