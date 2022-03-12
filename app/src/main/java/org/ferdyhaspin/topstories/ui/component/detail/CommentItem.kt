package org.ferdyhaspin.topstories.ui.component.detail

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import org.ferdyhaspin.topstories.R
import org.ferdyhaspin.topstories.data.model.Item
import org.ferdyhaspin.topstories.databinding.ItemCommentBinding

/**
 * Created by ferdyhaspin on 13/03/22.
 */
class CommentItem(
    private val item: Item
) : BindableItem<ItemCommentBinding>() {

    override fun getLayout() = R.layout.item_comment

    override fun bind(viewBinding: ItemCommentBinding, position: Int) {
        viewBinding.tvText.text = item.text
        viewBinding.tvBy.text = item.by
    }

    override fun initializeViewBinding(view: View): ItemCommentBinding {
        return ItemCommentBinding.bind(view)
    }

}