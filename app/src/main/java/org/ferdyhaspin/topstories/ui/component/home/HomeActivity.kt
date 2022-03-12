package org.ferdyhaspin.topstories.ui.component.home

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import net.matsudamper.viewbindingutil.inflateViewBinding
import org.ferdyhaspin.topstories.R
import org.ferdyhaspin.topstories.data.Resource
import org.ferdyhaspin.topstories.data.model.Item
import org.ferdyhaspin.topstories.databinding.ActivityHomeBinding
import org.ferdyhaspin.topstories.ui.base.BaseActivity
import org.ferdyhaspin.topstories.ui.component.detail.DetailActivity
import org.ferdyhaspin.topstories.utils.observe
import org.ferdyhaspin.topstories.utils.toGoneIf
import org.ferdyhaspin.topstories.utils.toast

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    override val binding by lazy { inflateViewBinding<ActivityHomeBinding>() }

    private val viewModel by viewModels<HomeViewModel>()

    override fun onReadyAction() {

    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorite()
    }

    override fun onObserveAction() {
        val mAdapter = GroupAdapter<GroupieViewHolder>()
        binding.rvTopStories.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 2)
            adapter = mAdapter
        }

        observe(viewModel.items) {
            setLoading(false)

            when (it) {
                is Resource.Loading -> setLoading(true)
                is Resource.DataError -> toast("Something when wrong")
                is Resource.Success -> {
                    val item = it.data
                    if (item != null) {
                        mAdapter.add(HomeItem(item) {
                            toDetail(item)
                        })
                        mAdapter.notifyItemInserted(mAdapter.itemCount)
                    }
                }
            }
        }

        observe(viewModel.favItem) { item ->
            if (item.id != 0) {
                binding.tvFavorite.text = item.title
                binding.tvFavorite.setOnClickListener {
                    toDetail(item = item)
                }
            } else {
                binding.tvFavorite.text = getString(R.string.label_favorite_not_set)
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.toGoneIf(!isLoading)
    }

    private fun toDetail(item: Item) {
        val intent = DetailActivity.newIntent(this, item)
        startActivity(intent)
    }
}