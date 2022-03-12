package org.ferdyhaspin.topstories.ui.component.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.ferdyhaspin.topstories.data.repository.StoryRepository
import javax.inject.Inject

/**
 * Created by ferdyhaspin on 12/03/22.
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    repository: StoryRepository
) : ViewModel() {


}