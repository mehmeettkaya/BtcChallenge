package com.mehmetkaya.btcchallenge.widget

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.mehmetkaya.btcchallenge.R
import com.mehmetkaya.btcchallenge.utils.orZero

class CustomMarker(
    context: Context,
    layoutResource: Int
) : MarkerView(context, layoutResource) {

    override fun refreshContent(entry: Entry?, highlight: Highlight?) {
        val y = entry?.y?.toDouble().orZero()
        findViewById<TextView>(R.id.tvMarker).text = context.getString(R.string.value_format, y)
        super.refreshContent(entry, highlight)
    }

    override fun getOffsetForDrawingAtPoint(xpos: Float, ypos: Float): MPPointF {
        return MPPointF(-width / 2f, -height - 10f)
    }
}
