package org.ferdyhaspin.topstories.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.ferdyhaspin.topstories.data.Event

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this) { it?.let { t -> action(t) } }
}

fun <T> LiveData<T>.post(data: T) = (this as MutableLiveData<T>).postValue(data)