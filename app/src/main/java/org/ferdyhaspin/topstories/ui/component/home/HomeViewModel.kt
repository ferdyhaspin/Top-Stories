package org.ferdyhaspin.topstories.ui.component.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.ferdyhaspin.topstories.data.repository.StoryRepository
import org.ferdyhaspin.topstories.utils.Coroutines
import javax.inject.Inject

/**
 * Created by ferdyhaspin on 12/03/22.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: StoryRepository
) : ViewModel() {

    val items = repository.items
    val favItem = repository.favItem

    init {
        Coroutines.io {
            repository.getItems()
        }
    }

    fun getFavorite() = Coroutines.io {
        repository.getFavorite()
    }
}