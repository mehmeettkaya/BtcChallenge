package com.mehmetkaya.btcchallenge.ui.pairlist

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mehmetkaya.btcchallenge.R
import com.mehmetkaya.btcchallenge.databinding.ItemFavoriteBinding
import com.mehmetkaya.btcchallenge.domain.model.Favorite
import com.mehmetkaya.btcchallenge.ui.pairlist.FavoriteListAdapter.FavoriteListItems
import com.mehmetkaya.btcchallenge.ui.pairlist.FavoriteListAdapter.FavoriteListItems.FavoriteItem
import com.mehmetkaya.btcchallenge.ui.pairlist.FavoriteListAdapter.FavoriteListItems.InfoItem
import com.mehmetkaya.btcchallenge.utils.inflater
import kotlin.math.absoluteValue

class FavoriteListAdapter(
    private val onItemClicked: ((FavoriteItem) -> Unit)? = null
) : ListAdapter<FavoriteListItems, ViewHolder>(FavoriteListDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        ITEM_FAVORITE_VIEW_TYPE -> FavoriteItemViewHolder(
            ItemFavoriteBinding.inflate(
                parent.context.inflater,
                parent,
                false
            )
        )
        ITEM_INFO_VIEW_TYPE -> InfoItemViewHolder(
            ItemFavoriteBinding.inflate(
                parent.context.inflater,
                parent,
                false
            )
        )
        else -> throw IllegalStateException("Unexpected view type!")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is FavoriteItemViewHolder -> holder.bind(item as FavoriteItem)
            is InfoItemViewHolder -> holder.bind(item as InfoItem)
            else -> throw IllegalStateException("Unexpected view type!")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is FavoriteItem -> ITEM_FAVORITE_VIEW_TYPE
            InfoItem -> ITEM_INFO_VIEW_TYPE
            else -> throw IllegalStateException("Unexpected view type!")
        }
    }

    inner class FavoriteItemViewHolder(
        private val binding: ItemFavoriteBinding
    ) : ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: FavoriteItem) = with(binding) {
            tvPairName.text = item.pairName

            tvLast.apply {
                text = context.getString(R.string.value_format, item.favorite.last)
            }

            tvDailyPercent.apply {
                text = context.getString(R.string.percentage_format, item.dailyPercent)
                setTextColor(ContextCompat.getColor(context, item.dailyPercentColorResId))
            }

            root.setOnClickListener {
                onItemClicked?.invoke(item)
            }
        }
    }

    inner class InfoItemViewHolder(
        private val binding: ItemFavoriteBinding
    ) : ViewHolder(binding.root) {

        fun bind(item: InfoItem) = with(binding) {
            tvPairName.setText(R.string.pair_name)
            tvLast.setText(R.string.last)

            tvDailyPercent.apply {
                setText(R.string.daily_percent)
                setTextColor(ContextCompat.getColor(context, R.color.thunderbird))
            }
        }
    }

    object FavoriteListDiffCallback : DiffUtil.ItemCallback<FavoriteListItems>() {
        override fun areItemsTheSame(
            oldItem: FavoriteListItems,
            newItem: FavoriteListItems
        ): Boolean {
            return when {
                oldItem is FavoriteItem && newItem is FavoriteItem -> {
                    oldItem.favorite == newItem.favorite
                }
                oldItem is InfoItem && newItem is InfoItem -> true
                else -> false
            }
        }

        override fun areContentsTheSame(
            oldItem: FavoriteListItems,
            newItem: FavoriteListItems
        ): Boolean {
            return when {
                oldItem is FavoriteItem && newItem is FavoriteItem -> {
                    oldItem == newItem
                }
                oldItem is InfoItem && newItem is InfoItem -> true
                else -> false
            }
        }
    }

    sealed class FavoriteListItems {
        object InfoItem : FavoriteListItems()

        data class FavoriteItem(
            val favorite: Favorite
        ) : FavoriteListItems() {

            val pairName: String
                get() = favorite.pairName.replace("_", "/")

            val dailyPercent: Double
                get() = favorite.dailyPercent.absoluteValue

            val dailyPercentColorResId: Int
                get() = when {
                    favorite.dailyPercent > 0 -> R.color.fun_green
                    favorite.dailyPercent < 0 -> R.color.thunderbird
                    else -> R.color.alabaster
                }
        }
    }

    companion object {
        private const val ITEM_INFO_VIEW_TYPE = 0
        private const val ITEM_FAVORITE_VIEW_TYPE = 1
    }
}
