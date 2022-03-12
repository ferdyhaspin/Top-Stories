package org.ferdyhaspin.topstories.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.ferdyhaspin.topstories.BuildConfig
import org.ferdyhaspin.topstories.data.Error
import org.ferdyhaspin.topstories.data.Resource
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
) : SafeApiRequest() {

    val items: LiveData<Resource<Item>> = MutableLiveData()

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
}