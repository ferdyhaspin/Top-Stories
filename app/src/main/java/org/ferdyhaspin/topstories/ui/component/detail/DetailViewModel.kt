package org.ferdyhaspin.topstories.ui.component.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.ferdyhaspin.topstories.data.model.Item
import org.ferdyhaspin.topstories.data.repository.StoryRepository
import org.ferdyhaspin.topstories.utils.Coroutines
import javax.inject.Inject

/**
 * Created by ferdyhaspin on 12/03/22.
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: StoryRepository
) : ViewModel() {

    val comments = repository.comments
    val isFavData = repository.isFavoriteData

    fun checkFavorite(item: Item) = Coroutines.io {
        repository.isFavoriteData(item)
    }

    fun actionFavorite(item: Item) = Coroutines.io {
        repository.actionFavorite(item)
    }

    fun getComments(comments: List<Int>) = Coroutines.io {
        repository.getComments(comments)
    }


}