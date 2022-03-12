package org.ferdyhaspin.topstories.ui.component.detail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.matsudamper.viewbindingutil.inflateViewBinding
import org.ferdyhaspin.topstories.data.model.Item
import org.ferdyhaspin.topstories.databinding.ActivityDetailBinding
import org.ferdyhaspin.topstories.ui.base.BaseActivity
import org.ferdyhaspin.topstories.utils.toJson
import org.ferdyhaspin.topstories.utils.toast

@AndroidEntryPoint
class DetailActivity : BaseActivity() {

    companion object {
        private const val EXTRA_ITEM = "extra_item"

        fun newIntent(context: Context, item: Item): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_ITEM, item)
            }
    }

    override val binding by lazy { inflateViewBinding<ActivityDetailBinding>() }

    private val viewModel by viewModels<DetailViewModel>()

    override fun onReadyAction() {
        toast(intent.getParcelableExtra<Item>(EXTRA_ITEM).toJson())
    }

    override fun onObserveAction() {

    }

}