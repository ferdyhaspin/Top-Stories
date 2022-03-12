package org.ferdyhaspin.topstories.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Item(
    val score: Int? = null,
    val by: String? = null,
    val id: Int? = null,
    val time: Int? = null,
    val title: String? = null,
    val type: String? = null,
    val descendants: Int? = null,
    val url: String? = null,
    val kids: List<Int?>? = null
) : Parcelable
