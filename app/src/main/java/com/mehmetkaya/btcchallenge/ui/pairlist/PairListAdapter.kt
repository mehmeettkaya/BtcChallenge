package com.mehmetkaya.btcchallenge.ui.pairlist

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mehmetkaya.btcchallenge.R
import com.mehmetkaya.btcchallenge.databinding.ItemPairBinding
import com.mehmetkaya.btcchallenge.domain.model.Ticker
import com.mehmetkaya.btcchallenge.ui.pairlist.PairListAdapter.PairListItems
import com.mehmetkaya.btcchallenge.ui.pairlist.PairListAdapter.PairListItems.InfoItem
import com.mehmetkaya.btcchallenge.ui.pairlist.PairListAdapter.PairListItems.PairItem
import com.mehmetkaya.btcchallenge.utils.inflater
import kotlin.math.absoluteValue

class PairListAdapter(
    private val onFavoriteClicked: ((PairItem) -> Unit)? = null,
    private val onItemClicked: ((PairItem) -> Unit)? = null
) : ListAdapter<PairListItems, ViewHolder>(PairListDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        ITEM_PAIR_VIEW_TYPE -> PairItemViewHolder(
            ItemPairBinding.inflate(
                parent.context.inflater,
                parent,
                false
            )
        )
        ITEM_INFO_VIEW_TYPE -> InfoItemViewHolder(
            ItemPairBinding.inflate(
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
            is PairItemViewHolder -> holder.bind(item as PairItem)
            is InfoItemViewHolder -> holder.bind(item as InfoItem)
            else -> throw IllegalStateException("Unexpected view type!")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PairItem -> ITEM_PAIR_VIEW_TYPE
            InfoItem -> ITEM_INFO_VIEW_TYPE
            else -> throw IllegalStateException("Unexpected view type!")
        }
    }

    inner class PairItemViewHolder(
        private val binding: ItemPairBinding
    ) : ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: PairItem) = with(binding) {
            ivFavorite.apply {
                setImageResource(item.favoriteImageResId)
                setOnClickListener { onFavoriteClicked?.invoke(item) }
            }
            tvPairName.text = item.pairName

            tvLast.apply {
                text = context.getString(R.string.value_format, item.ticker.last)
            }

            tvDailyPercent.apply {
                text = context.getString(R.string.percentage_format, item.dailyPercent)
                setTextColor(ContextCompat.getColor(context, item.dailyPercentColorResId))
            }

            tvVolumeAndNumeratorName.apply {
                val formattedVolume = context.getString(R.string.value_format, item.ticker.volume)
                text = "$formattedVolume ${item.ticker.numeratorSymbol}"
            }

            root.setOnClickListener {
                onItemClicked?.invoke(item)
            }
        }
    }

    inner class InfoItemViewHolder(
        private val binding: ItemPairBinding
    ) : ViewHolder(binding.root) {

        fun bind(item: InfoItem) = with(binding) {
            ivFavorite.setImageResource(R.drawable.ic_gray_star)
            tvPairName.setText(R.string.pair_name)
            tvLast.setText(R.string.last)
            tvVolumeAndNumeratorName.setText(R.string.volume_and_numerator_name)

            tvDailyPercent.apply {
                setText(R.string.daily_percent)
                setTextColor(ContextCompat.getColor(context, R.color.fun_green))
            }
        }
    }

    object PairListDiffCallback : DiffUtil.ItemCallback<PairListItems>() {
        override fun areItemsTheSame(
            oldItem: PairListItems,
            newItem: PairListItems
        ): Boolean {
            return when {
                oldItem is PairItem && newItem is PairItem -> {
                    oldItem.ticker == newItem.ticker
                }
                oldItem is InfoItem && newItem is InfoItem -> true
                else -> false
            }
        }

        override fun areContentsTheSame(
            oldItem: PairListItems,
            newItem: PairListItems
        ): Boolean {
            return when {
                oldItem is PairItem && newItem is PairItem -> {
                    oldItem == newItem
                }
                oldItem is InfoItem && newItem is InfoItem -> true
                else -> false
            }
        }
    }

    sealed class PairListItems {
        object InfoItem : PairListItems()

        data class PairItem(
            val ticker: Ticker,
            val isFavorite: Boolean
        ) : PairListItems() {

            val favoriteImageResId: Int
                get() = if (isFavorite) R.drawable.ic_yellow_star else R.drawable.ic_gray_star

            val pairName: String
                get() = ticker.pairNormalized.replace("_", "/")

            val dailyPercent: Double
                get() = ticker.dailyPercent.absoluteValue

            val dailyPercentColorResId: Int
                get() = when {
                    ticker.dailyPercent > 0 -> R.color.fun_green
                    ticker.dailyPercent < 0 -> R.color.thunderbird
                    else -> R.color.manatee
                }
        }
    }

    companion object {
        private const val ITEM_INFO_VIEW_TYPE = 0
        private const val ITEM_PAIR_VIEW_TYPE = 1
    }
}
