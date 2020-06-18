package com.example.tiku1.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiku1.AppClient;
import com.example.tiku1.R;
import com.example.tiku1.bean.YZ;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-06-06 ：）
 */
@SuppressLint("ValidFragment")
public class SSXS_Fragment1 extends Fragment {
    private AppClient appClient;

    public SSXS_Fragment1(AppClient appClient) {
        this.appClient = appClient;
    }

    @BindView(R.id.chart_title)
    TextView chartTitle;
    @BindView(R.id.line_chart)
    LineChart lineChart;
    Unbinder unbinder;
    private boolean isLoop = true;
    private List<YZ> yzs;
    private List<Entry> entries;
    private List<String> xVaule;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            setDate();
            return false;
        }
    });

    private void setDate() {
        yzs = appClient.getYzs();
        if (yzs.size() == 0) {
            return;
        }
        if (entries == null) {
            entries = new ArrayList<>();
            xVaule = new ArrayList<>();
        } else {
            entries.clear();
            xVaule.clear();
        }
        for (int i = 0; i < yzs.size(); i++) {
            YZ yz = yzs.get(i);
            int wd = yz.getTemperature();
            entries.add(new Entry(i,wd));
            xVaule.add(yz.getTime());
        }
        LineDataSet dataSet = new LineDataSet(entries,"");
        dataSet.setCircleColor(Color.GRAY);
        dataSet.setColor(Color.GRAY);
        dataSet.setCircleRadius(7);
        dataSet.setDrawCircleHole(false);
        LineData data = new LineData(dataSet);
        setX();
        setY();
        lineChart.setData(data);
        lineChart.getDescription().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.invalidate();


    }

    private void setY() {
        YAxis yAxis = lineChart.getAxisRight();
        yAxis.setAxisMinimum(0f);
        yAxis.setEnabled(false);
        YAxis yAxis1 = lineChart.getAxisLeft();
        yAxis1.setAxisMinimum(0);
        yAxis1.setAxisMaximum(45);
    }

    private void setX() {
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVaule));
        xAxis.setTextSize(20f);
        xAxis.setLabelRotationAngle(90);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(xVaule.size());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ssxs_framgnet, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        chartTitle.setText("温度");
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (isLoop);
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoop = false;
        unbinder.unbind();
    }
}
