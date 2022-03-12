package org.ferdyhaspin.topstories.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.ferdyhaspin.topstories.BuildConfig
import org.ferdyhaspin.topstories.data.Error
import org.ferdyhaspin.topstories.data.Resource
import org.ferdyhaspin.topstories.data.local.dao.StoryDao
import org.ferdyhaspin.topstories.data.model.Item
import org.ferdyhaspin.topstories.data.remote.config.SafeApiRequest
import org.ferdyhaspin.topstories.data.remote.config.ServiceGenerator
import org.ferdyhaspin.topstories.data.remote.service.StoryService
import org.ferdyhaspin.topstories.utils.post
import javax.inject.Inject

/**
 * Created by ferdyhaspin on 12/03/22.
 */
class StoryRepository @Inject constructor(
    service: ServiceGenerator,
    private val storyDao: StoryDao
) : SafeApiRequest() {

    val items: LiveData<Resource<Item>> = MutableLiveData()
    val favItem: LiveData<Item> = MutableLiveData()

    val comments: LiveData<Resource<List<Item>>> = MutableLiveData()
    val isFavoriteData: LiveData<Boolean> = MutableLiveData()

    private val service = service.createService(
        StoryService::class.java,
        BuildConfig.BASE_URL
    )

    suspend fun getItems() {
        items.post(Resource.Loading())
        try {
            val listId = apiRequest { service.getTopStories() }

            listId.forEach {
                val data = apiRequest { service.getItem(it.toString()) }
                items.post(Resource.Success(data))
            }

        } catch (e: Exception) {
            items.post(Resource.DataError(Error(e)))
            e.printStackTrace()
        }
    }

    suspend fun getFavorite() {
        val favData = storyDao.getFavorite() ?: Item()
        favItem.post(favData)
    }

    private fun setFavoriteData(favorite: Boolean) {
        isFavoriteData.post(favorite)
    }

    suspend fun isFavoriteData(item: Item) {
        val favData = storyDao.getFavorite() ?: Item()
        setFavoriteData(favData == item)
    }

    suspend fun actionFavorite(item: Item) {
        if (isFavoriteData.value == true) {
            deleteFavorite(item)
        } else {
            insertFavorite(item)
        }
    }

    private suspend fun insertFavorite(item: Item) {
        storyDao.updateInsert(item)
        setFavoriteData(true)
    }

    private suspend fun deleteFavorite(item: Item) {
        storyDao.delete(item)
        setFavoriteData(false)
    }

    suspend fun getComments(ids: List<Int>) {
        comments.post(Resource.Loading())
        try {
            val content = mutableListOf<Item>()
            ids.forEach {
                val data = apiRequest { service.getItem(it.toString()) }
                content.add(data)
            }
            comments.post(Resource.Success(content))

        } catch (e: Exception) {
            comments.post(Resource.DataError(Error(e)))
            e.printStackTrace()
        }
    }
}