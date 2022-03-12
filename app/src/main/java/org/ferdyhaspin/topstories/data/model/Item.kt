package org.ferdyhaspin.topstories.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "item")
data class Item(
    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("score")
    var score: Int = 0,
    @SerializedName("by")
    var by: String = "",
    @SerializedName("time")
    var time: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("descendants")
    var descendants: Int = 0,
    @SerializedName("url")
    var url: String = "",

    @SerializedName("kids")
    var kids: List<Int> = mutableListOf(),

    @Ignore
    @SerializedName("text")
    var text: String = "",
) : Parcelable
