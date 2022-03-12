package org.ferdyhaspin.topstories.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.ferdyhaspin.topstories.data.local.dao.StoryDao
import org.ferdyhaspin.topstories.data.model.Item

/**
 * Created by ferdyhaspin on 12/03/22.
 */

@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(KidConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storyDao(): StoryDao

}