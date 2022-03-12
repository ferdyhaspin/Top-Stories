package org.ferdyhaspin.topstories.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import org.ferdyhaspin.topstories.utils.toJson

/**
 * Created by ferdyhaspin on 12/03/22.
 */


@Suppress("unused")
object KidConverter {

    @JvmStatic
    @TypeConverter
    fun stringToObject(value: String): List<Int> {
        return Gson().fromJson(value, Array<Int>::class.java).asList()
    }

    @JvmStatic
    @TypeConverter
    fun objectToString(list: List<Int>) = list.toJson()
}