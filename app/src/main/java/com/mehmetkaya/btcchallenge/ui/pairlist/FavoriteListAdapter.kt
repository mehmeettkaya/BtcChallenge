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
import com.mehmetkaya.btcchallenge.ui.pairlist.FavoriteListAdapter.FavoriteListItem
import com.mehmetkaya.btcchallenge.ui.pairlist.FavoriteListAdapter.FavoriteListItemViewHolder
import com.mehmetkaya.btcchallenge.utils.inflater
import kotlin.math.absoluteValue

class FavoriteListAdapter(
    private val onItemClicked: ((FavoriteListItem) -> Unit)? = null
) : ListAdapter<FavoriteListItem, FavoriteListItemViewHolder>(FavoriteListDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoriteListItemViewHolder(
        ItemFavoriteBinding.inflate(
            parent.context.inflater,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: FavoriteListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class FavoriteListItemViewHolder(
        private val binding: ItemFavoriteBinding
    ) : ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: FavoriteListItem) = with(binding) {
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

    object FavoriteListDiffCallback : DiffUtil.ItemCallback<FavoriteListItem>() {
        override fun areItemsTheSame(
            oldItem: FavoriteListItem,
            newItem: FavoriteListItem
        ): Boolean {
            return oldItem.favorite.pairName == newItem.favorite.pairName
        }

        override fun areContentsTheSame(
            oldItem: FavoriteListItem,
            newItem: FavoriteListItem
        ): Boolean {
            return oldItem.favorite == newItem.favorite
        }
    }

    data class FavoriteListItem(
        val favorite: Favorite
    ) {

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
