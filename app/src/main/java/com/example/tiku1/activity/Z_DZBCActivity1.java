package com.example.tiku1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku1.AppClient;
import com.example.tiku1.R;
import com.example.tiku1.bean.DZBC;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.LunarCalendar;
import com.haibin.calendarview.LunarUtil;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-11 at 15:50 ：）
 */
public class Z_DZBCActivity1 extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.left_image)
    ImageView leftImage;
    @BindView(R.id.bus_line)
    TextView busLine;
    @BindView(R.id.bus_price)
    TextView busPrice;
    @BindView(R.id.bus_mileage)
    TextView busMileage;
    @BindView(R.id.bt_next)
    Button btNext;
    private DZBC dzbc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc_layout1);
        ButterKnife.bind(this);
        AppClient.addActivity(this);
        dzbc = (DZBC) getIntent().getSerializableExtra("info");
        busLine.setText(dzbc.getBusline().get(0)+"--"+dzbc.getBusline().get(dzbc.getBusline().size()-1));
        busPrice.setText("票价：¥"+dzbc.getFares());
        busMileage.setText("里程:"+dzbc.getMileage()+".0km");
        if (dzbc.getId()%2==0){
            leftImage.setImageResource(R.mipmap.ditu);
        }else{
            leftImage.setImageResource(R.mipmap.ditu2);
        }
        title.setText("定制班车");
    }

    @OnClick({R.id.change, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.bt_next:
                Intent intent = new Intent(this,Z_DZBCActivity2.class);
                intent.putExtra("info",dzbc);
                startActivity(intent);
                break;
        }
    }
}
