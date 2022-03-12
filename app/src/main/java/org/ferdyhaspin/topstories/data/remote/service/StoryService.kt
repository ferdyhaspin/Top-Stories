package org.ferdyhaspin.topstories.data.remote.service

import org.ferdyhaspin.topstories.data.model.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by ferdyhaspin on 12/03/22.
 */
interface StoryService {

    @Headers("Content-Type: application/json")
    @GET("topstories.json?print=pretty")
    suspend fun getTopStories(): Response<List<Int>>

    @Headers("Content-Type: application/json")
    @GET("item/{id}.json?print=pretty")
    suspend fun getItem(@Path("id") id: String): Response<Item>
}