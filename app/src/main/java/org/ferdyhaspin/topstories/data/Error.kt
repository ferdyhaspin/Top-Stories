package org.ferdyhaspin.topstories.data

import com.google.gson.annotations.SerializedName

class Error(
    @SerializedName("code") val code: Int,
    @SerializedName("description") val description: String
) {

    constructor(exception: Exception) : this(
        code = -1, description = exception.message ?: ""
    )
}