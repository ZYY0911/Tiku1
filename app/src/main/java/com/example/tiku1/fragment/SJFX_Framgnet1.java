package com.example.tiku1.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiku1.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-11 at 14:44 ：）
 */
@SuppressLint("ValidFragment")
public class SJFX_Framgnet1 extends Fragment {
    private Map<String ,Integer> map;
    private int all;
    private List<PieEntry> pieEntries;
    private List<Integer> colors;
    private float y ,n;
    public SJFX_Framgnet1(Map<String, Integer> map, int all) {
        this.map = map;
        this.all = all;
    }

    @BindView(R.id.pie_chart)
    PieChart pieChart;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInsceState) {
        View view = inflater.inflate(R.layout.sjfx_fragment1, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pieEntries = new ArrayList<>();
        colors = new ArrayList<>();
        y = (float)map.size()/(float) all;
        n = 1-y;
        pieEntries.add(new PieEntry(y,"有重复违章"));
        pieEntries.add(new PieEntry(n,"无重复违章"));
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        dataSet.setColors(colors);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1Length(1f);
        dataSet.setValueLinePart2Length(1f);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0.0");
                return format.format(value)+"%";
            }
        });
        dataSet.setValueTextSize(25);
        dataSet.setValueLinePart1OffsetPercentage(50);
        dataSet.setValueLineColor(Color.BLACK);
        dataSet.setSliceSpace(3f);
        PieData data = new PieData(dataSet);
        Legend legend = pieChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setTextSize(25);
        legend.setFormSize(25);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setEntryLabelTextSize(25);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setUsePercentValues(true);
        pieChart.setTouchEnabled(false);
        pieChart.invalidate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
