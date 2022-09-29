package com.mehmetkaya.btcchallenge.ui.pairchart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.animation.Easing.EaseInExpo
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.mehmetkaya.btcchallenge.R
import com.mehmetkaya.btcchallenge.databinding.FragmentPairChartBinding
import com.mehmetkaya.btcchallenge.utils.collectEvent
import com.mehmetkaya.btcchallenge.utils.collectState
import com.mehmetkaya.btcchallenge.utils.viewBinding
import com.mehmetkaya.btcchallenge.widget.CustomMarker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PairChartFragment : Fragment() {

    private val binding by viewBinding(FragmentPairChartBinding::bind)

    private val viewModel: PairChartViewModel by viewModels()

    private val args: PairChartFragmentArgs by navArgs()

    private val customMarker by lazy {
        CustomMarker(requireContext(), R.layout.view_marker)
    }

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
        viewModel.init(args.pairName)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetch()
    }

    private fun initView() = with(binding) {

    }

    private fun renderView(uiState: PairChartUiState) = with(binding) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = uiState.symbol

        val lineDataSet = LineDataSet(uiState.entries, uiState.symbol).apply {
            setDrawFilled(true)
            setDrawValues(false)
            setDrawCircles(false)
            lineWidth = 2f
            fillDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.bg_pair_chart)
        }

        lineChart.apply {
            data = LineData(lineDataSet)
            axisLeft.isEnabled = false
            axisRight.textColor = ContextCompat.getColor(context, R.color.alabaster)
            axisRight.setDrawAxisLine(false)
            xAxis.isEnabled = false
            description.isEnabled = false
            isDoubleTapToZoomEnabled = false
            legend.isEnabled = false
            marker = customMarker
            setTouchEnabled(true)
            setPinchZoom(false)
            animateX(1000, EaseInExpo)
        }
    }

    private fun handleEvent(uiEvent: PairChartUiEvent) {
        when (uiEvent) {
            else -> Unit
        }
    }
}
