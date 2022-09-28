package com.mehmetkaya.btcchallenge.ui.pairchart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mehmetkaya.btcchallenge.R
import com.mehmetkaya.btcchallenge.databinding.FragmentPairChartBinding
import com.mehmetkaya.btcchallenge.utils.collectEvent
import com.mehmetkaya.btcchallenge.utils.collectState
import com.mehmetkaya.btcchallenge.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PairChartFragment : Fragment() {

    private val binding by viewBinding(FragmentPairChartBinding::bind)

    private val viewModel: PairChartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pair_chart, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectState(viewModel.uiState, ::renderView)
        collectEvent(viewModel.uiEvent, ::handleEvent)
    }


    private fun initView() = with(binding) {

    }

    private fun renderView(uiState: PairChartUiState) = with(binding) {

    }

    private fun handleEvent(uiEvent: PairChartUiEvent) {
        when (uiEvent) {
            else -> Unit
        }
    }
}
