package org.ferdyhaspin.topstories.utils

import android.util.Log

fun logDebug(tag: String, massage: String) {
//    if (BuildConfig.DEBUG) {
        Log.d(tag, massage)
//    }
}

fun logInfo(tag: String, massage: String) {
//    if (BuildConfig.DEBUG) {
    Log.i(tag, massage)
//    }
}

fun logError(tag: String, massage: String) {
//    if (BuildConfig.DEBUG) {
    Log.e(tag, massage)
//    }
}
