package org.ferdyhaspin.topstories.ui.component.detail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import net.matsudamper.viewbindingutil.inflateViewBinding
import org.ferdyhaspin.topstories.R
import org.ferdyhaspin.topstories.data.Resource
import org.ferdyhaspin.topstories.data.model.Item
import org.ferdyhaspin.topstories.databinding.ActivityDetailBinding
import org.ferdyhaspin.topstories.ui.base.BaseActivity
import org.ferdyhaspin.topstories.utils.getDate
import org.ferdyhaspin.topstories.utils.observe
import org.ferdyhaspin.topstories.utils.toGoneIf
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
    private val item by lazy { intent.getParcelableExtra(EXTRA_ITEM) ?: Item() }

    override fun onReadyAction() {
        binding.tvTitle.text = item.title

        val date = "By ${item.by}\n${item.time.toLong().getDate()}"
        binding.tvDate.text = date

        binding.ibInsertFav.setOnClickListener {
            viewModel.actionFavorite(item)
        }

        viewModel.checkFavorite(item)
        viewModel.getComments(item.kids)

    }

    override fun onObserveAction() {
        observe(viewModel.isFavData) {
            val color = if (it) {
                R.color.colorAccent
            } else {
                R.color.colorGrey
            }
            binding.ibInsertFav.setColorFilter(ContextCompat.getColor(this, color))
        }

        observe(viewModel.comments) {
            setLoading(false)

            when (it) {
                is Resource.Loading -> setLoading(true)
                is Resource.DataError -> toast("Something when wrong")
                is Resource.Success -> {
                    val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
                        it.data?.let { comments ->
                            val data = comments.map { item ->
                                CommentItem(item)
                            }
                            addAll(data)
                        }

                    }

                    binding.rvComment.apply {
                        layoutManager = LinearLayoutManager(this@DetailActivity)
                        adapter = mAdapter
                    }
                }
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.toGoneIf(!isLoading)
    }

}