package com.example.bumiajimobileview

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class Fragment_chart : Fragment() {

    private lateinit var barChart: BarChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chart, container, false)
        barChart = view.findViewById(R.id.barChart)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart()
    }

    private fun setupChart() {
        val entries = listOf(
            BarEntry(0f, 10f), // A
            BarEntry(1f, 20f), // B
            BarEntry(2f, 15f), // C
            BarEntry(3f, 5f)   // Reject
        )

        val labels = listOf("A", "B", "C", "Reject")

        val dataSet = BarDataSet(entries, "A, B, C, Reject")
        dataSet.stackLabels = labels.toTypedArray()

        // Define colors for bars
        val colors = mutableListOf<Int>()
        colors.add(Color.BLUE)   // Color for A
        colors.add(Color.GREEN)  // Color for B
        colors.add(Color.RED)    // Color for C
        colors.add(Color.GRAY)   // Color for Reject
        dataSet.colors = colors

        val data = BarData(dataSet)

        barChart.data = data
        barChart.invalidate()

        // Customize chart if needed
        barChart.description.isEnabled = false
        barChart.setFitBars(true)
        barChart.setPinchZoom(false)
        barChart.animateY(1000)

        // Enable Legend
        val legend = barChart.legend
        legend.isEnabled = true
    }



}
