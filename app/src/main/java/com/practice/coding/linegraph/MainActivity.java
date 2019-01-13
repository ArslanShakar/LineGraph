package com.practice.coding.linegraph;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;
    Integer[] yData = {25, 50, 70, 50, 85, 60, 45, 75, 50, 65};
    String[] xMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChart = findViewById(R.id.lineChart);
        lineChart.setScaleEnabled(false);
        lineChart.setDragEnabled(true);
        lineChart.enableScroll();
        LimitLine upperLimit = new LimitLine(75f, "Excellent");
        upperLimit.setLineWidth(2);
        upperLimit.enableDashedLine(10f, 10f, 0f);
        upperLimit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upperLimit.setTextSize(14f);

        LimitLine lowerLimit = new LimitLine(50f, "Too Low");
        lowerLimit.setLineWidth(2);
        lowerLimit.enableDashedLine(10f, 10f, 0f);
        lowerLimit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lowerLimit.setTextSize(14f);

        YAxis yLeftAxes = lineChart.getAxisLeft();
        yLeftAxes.removeAllLimitLines();
        yLeftAxes.addLimitLine(upperLimit);
        yLeftAxes.addLimitLine(lowerLimit);
        yLeftAxes.setAxisMaximum(100f);
        yLeftAxes.setAxisMinimum(0f);

        yLeftAxes.enableGridDashedLine(10f, 10f, 0f);
        yLeftAxes.setDrawGridLinesBehindData(true);

        lineChart.getAxisRight().setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxesValuesFormatter(xMonths));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);

        addData();

    }

    private void addData() {
        ArrayList<Entry> yEntries = new ArrayList<>();

        yEntries.add(new Entry(0, 45f));
        yEntries.add(new Entry(1, 60f));
        yEntries.add(new Entry(2, 30f));
        yEntries.add(new Entry(3, 70f));
        yEntries.add(new Entry(4, 80f));
        yEntries.add(new Entry(5, 90f));

        LineDataSet set1 = new LineDataSet(yEntries, "Math");
        set1.setLineWidth(3);
        set1.setValueTextColor(Color.GREEN);
        set1.setValueTextSize(12f);
        set1.setFillAlpha(110);
        set1.setColor(Color.GREEN);

        ArrayList<Entry> y2Entries = new ArrayList<>();
        for (int i = 0; i < yData.length; i++) {
            y2Entries.add(new Entry(i, yData[i]));
        }

        LineDataSet set2 = new LineDataSet(y2Entries, "English");
        set2.setLineWidth(3);
        set2.setValueTextColor(Color.MAGENTA);
        set2.setValueTextSize(12f);
        set2.setFillAlpha(110);
        set2.setColor(Color.MAGENTA);

       /* ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData lineData = new LineData(dataSets);*/

        LineData lineData = new LineData(set1, set2);
        lineChart.setData(lineData);

    }

    private class MyXAxesValuesFormatter implements IAxisValueFormatter {
        private String[] mValues;

        public MyXAxesValuesFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }
    }
}
