package com.mehmetkaya.btcchallenge.ui.pairlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mehmetkaya.btcchallenge.R
import com.mehmetkaya.btcchallenge.databinding.FragmentPairListBinding
import com.mehmetkaya.btcchallenge.ui.pairlist.PairListUiEvent.NavigateToPairChart
import com.mehmetkaya.btcchallenge.utils.collectEvent
import com.mehmetkaya.btcchallenge.utils.collectState
import com.mehmetkaya.btcchallenge.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PairListFragment : Fragment() {

    private val binding by viewBinding(FragmentPairListBinding::bind)

    private val viewModel: PairListViewModel by viewModels()

    private val pairListAdapter: PairListAdapter by lazy {
        PairListAdapter(
            onFavoriteClicked = { viewModel.onFavoriteClicked(it) },
            onItemClicked = { viewModel.onPairClicked(it.ticker.pair) },
        )
    }

    private val favoriteListAdapter: FavoriteListAdapter by lazy {
        FavoriteListAdapter {
            viewModel.onPairClicked(it.favorite.pairName)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pair_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectState(viewModel.uiState, ::renderView)
        collectEvent(viewModel.uiEvent, ::handleEvent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetch()
    }


    private fun initView() = with(binding) {
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.fetch()
        }

        rvFavorites.adapter = favoriteListAdapter
        rvPairs.adapter = pairListAdapter
    }

    private fun renderView(uiState: PairListUiState) = with(binding) {
        favoriteListAdapter.submitList(uiState.favoriteItems)
        pairListAdapter.submitList(uiState.pairItems)
        clFavorites.isVisible = uiState.isFavoriteLayoutVisible
    }

    private fun handleEvent(uiEvent: PairListUiEvent) {
        when (uiEvent) {
            is NavigateToPairChart -> {
                val directions = PairListFragmentDirections.navigateToPairChart(uiEvent.pairName)
                findNavController().navigate(directions)
            }
        }
    }
}
