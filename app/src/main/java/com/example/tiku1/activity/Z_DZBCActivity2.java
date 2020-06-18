package com.example.tiku1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku1.AppClient;
import com.example.tiku1.R;
import com.example.tiku1.adapter.RLAdapter;
import com.example.tiku1.bean.CCRQ;
import com.example.tiku1.bean.DZBC;
import com.example.tiku1.bean.RLBean;
import com.haibin.calendarview.LunarCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-11 at 19:36 ：）
 */
public class Z_DZBCActivity2 extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.my_gird)
    GridView myGird;
    @BindView(R.id.bus_time)
    TextView busTime;
    @BindView(R.id.bus_mileage)
    TextView busMileage;
    @BindView(R.id.bt_next)
    Button btNext;
    private List<RLBean> rlBeans;
    private List<Integer> bglist;
    private RLAdapter adapter;
    private List<CCRQ> ccrqs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc_layout2);
        ButterKnife.bind(this);
        AppClient.addActivity(this);
        title.setText("定制班车");
        initData();
    }

    private void initData() {
        rlBeans = new ArrayList<>();
        bglist = new ArrayList<>();
        ccrqs = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        for (int i = 0; i < week - 1; i++) {
            rlBeans.add(new RLBean("", "", 1, 0, false));
            bglist.add(1);
        }
        int nowday = day;
        for (int i = 0; i < 42; i++) {
            if (nowday > 30) {
                month++;
                nowday = 1;
            }
            com.haibin.calendarview.Calendar calendar1 = new com.haibin.calendarview.Calendar();
            calendar1.setYear(year);
            calendar1.setMonth(month);
            calendar1.setDay(nowday);
            rlBeans.add(new RLBean(nowday + "", LunarCalendar.getLunarText(calendar1), getWeek(year, month - 1, nowday), month, true));
            bglist.add( getWeek(year, month - 1, nowday));
            nowday++;
        }
        if (rlBeans.size() != 42) {
            int j = 49 - rlBeans.size();
            for (int i = 0; i < j; i++) {
                rlBeans.add(new RLBean("", "", 1, 0, false));
                bglist.add(1);
            }
        }
        adapter = new RLAdapter(this, rlBeans);
        myGird.setAdapter(adapter);
        adapter.setClickItem(new RLAdapter.ClickItem() {
            @Override
            public void myClick(int position, int bg) {
                if (bg == 0 || bg == 1) {
                    RLBean rlBean = rlBeans.get(position);
                    rlBean.setBg(2);
                    rlBeans.set(position, rlBean);
                    ccrqs.add(new CCRQ(position, "2020-" + rlBean.getMonth() + "-" + rlBean.getSolar()));
                    setDateTime();
                } else {
                    int start = bglist.get(position);
                    RLBean rlBean = rlBeans.get(position);
                    rlBean.setBg(start);
                    rlBeans.set(position, rlBean);
                    for (int i = 0; i < ccrqs.size(); i++) {
                        if (ccrqs.get(i).getPosition() == position) {
                            ccrqs.remove(i);
                        }
                    }
                    setDateTime();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setDateTime() {
        Collections.sort(ccrqs, new Comparator<CCRQ>() {
            @Override
            public int compare(CCRQ o1, CCRQ o2) {
                return o1.getPosition() - o2.getPosition();
            }
        });
        String str = "";
        for (int i = 0; i < ccrqs.size(); i++) {
            if (i == 0) {
                str = ccrqs.get(i).getMsg();
            } else {
                str += "," + ccrqs.get(i).getMsg();
            }
        }
        busTime.setText(str);
    }

    private int getWeek(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week == 1 || week == 7) {
            return 1;
        } else {
            return 0;
        }
    }

    @OnClick({R.id.change, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.bt_next:
                DZBC dzbc = (DZBC) getIntent().getSerializableExtra("info");
                Intent intent = new Intent(this,Z_DZBCActivity3.class);
                intent.putExtra("info",dzbc);
                intent.putExtra("date",busTime.getText());
                startActivity(intent);
                break;
        }
    }
}
