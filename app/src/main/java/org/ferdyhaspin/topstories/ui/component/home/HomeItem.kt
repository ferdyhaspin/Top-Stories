package org.ferdyhaspin.topstories.ui.component.home

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import org.ferdyhaspin.topstories.R
import org.ferdyhaspin.topstories.data.model.Item
import org.ferdyhaspin.topstories.databinding.ItemStoryBinding

/**
 * Created by ferdyhaspin on 12/03/22.
 */
class HomeItem(
    private val item: Item
) : BindableItem<ItemStoryBinding>() {

    override fun getLayout() = R.layout.item_story

    override fun bind(viewBinding: ItemStoryBinding, position: Int) {
        viewBinding.tvTitle.text = item.title
        viewBinding.tvComment.text = (item.kids?.size ?: 0).toString()
        viewBinding.tvScore.text = viewBinding.root.context
            .getString(R.string.label_score, item.score)
    }

    override fun initializeViewBinding(view: View): ItemStoryBinding {
        return ItemStoryBinding.bind(view)
    }

}