package com.example.tiku1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku1.AppClient;
import com.example.tiku1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-11 at 21:03 ：）
 */
public class Z_DZBCActivity4 extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bus_line)
    TextView busLine;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.bt_next)
    Button btNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc_layout4);
        ButterKnife.bind(this);
        AppClient.addActivity(this);
        title.setText("确认订单");
        busLine.setText("乘车线路:"+getIntent().getStringExtra("bus"));
        tvName.setText("乘客姓名:"+getIntent().getStringExtra("name"));
        tvTel.setText("手机号码:"+getIntent().getStringExtra("tel"));
        tvName.setText("乘车日期:"+getIntent().getStringExtra("date"));
        tvLocation.setText("上车地点:"+getIntent().getStringExtra("location"));
    }

    @OnClick({R.id.change, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.bt_next:
                AppClient.finaAll();
                break;
        }
    }
}
