package org.ferdyhaspin.topstories.data.local.dao

import androidx.room.*
import org.ferdyhaspin.topstories.data.model.Item

/**
 * Created by ferdyhaspin on 12/03/22.
 */

@Dao
abstract class StoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj: Item): Long

    @Update
    abstract fun update(obj: Item)

    @Delete
    abstract suspend fun delete(obj: Item)

    @Transaction
    open suspend fun updateInsert(obj: Item) {
        delete()
        insert(obj)
    }

    @Query("DELETE FROM item")
    abstract suspend fun delete()

    @Query("SELECT * FROM item LIMIT 1")
    abstract suspend fun getFavorite(): Item?

}