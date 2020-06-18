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
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-11 at 14:44 ：）
 */
@SuppressLint("ValidFragment")
public class SJFX_Framgnet2 extends Fragment {
    @BindView(R.id.bar_chart)
    HorizontalBarChart barChart;
    Unbinder unbinder;
    private int a, b, c;
    private List<BarEntry> pieEntries;
    private List<Integer> colors;
    private float A, B, C;
    private List<String> xValue;

    public SJFX_Framgnet2(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInsceState) {
        View view = inflater.inflate(R.layout.sjfx_fragment2, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pieEntries = new ArrayList<>();
        colors = new ArrayList<>();
        A = (float) a / (float) (a + b + c) * 100;
        B = (float) b / (float) (a + b + c) * 100;
        C = (float) c / (float) (a + b + c) * 100;
        pieEntries.add(new BarEntry(0, A));
        pieEntries.add(new BarEntry(1, B));
        pieEntries.add(new BarEntry(2, C));
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        BarDataSet dataSet = new BarDataSet(pieEntries, "");
        dataSet.setColors(colors);
        dataSet.setValueFormatter(new PercentFormatter());
        dataSet.setValueTextSize(25);
        dataSet.setValueTextColor(Color.RED);
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.3f);


        setX();
        setY();
        barChart.setData(data);
        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.setTouchEnabled(false);
        barChart.invalidate();
    }

    private void setY() {
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        yAxis.setEnabled(false);
        YAxis yAxis1 = barChart.getAxisRight();
        yAxis1.setAxisMinimum(0);
       // yAxis1.setLabelCount(8);
        yAxis1.setTextSize(10f);
        yAxis1.setValueFormatter(new PercentFormatter());


    }

    private void setX() {
        xValue = new ArrayList<>();
        xValue.add("1~3条违章");
        xValue.add("3~5条违章");
        xValue.add("5条以上违章");
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(20f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValue));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(3);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(-30);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
