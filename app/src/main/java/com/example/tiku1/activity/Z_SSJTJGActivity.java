package com.example.tiku1.activity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku1.R;
import com.example.tiku1.bean.BusHistory;
import com.example.tiku1.bean.SSJT;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-12 at 17:25 ：）
 */
public class Z_SSJTJGActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_time_start)
    TextView tvTimeStart;
    @BindView(R.id.tv_time_end)
    TextView tvTimeEnd;
    @BindView(R.id.all_station)
    TextView allStation;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.tv_bus_line)
    TextView tvBusLine;
    private SSJT ssjt;
    private List<String> busLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ssjtjg_layout);
        ButterKnife.bind(this);
     //   EventBus.getDefault().register(this);
        ssjt = (SSJT) getIntent().getSerializableExtra("info");
        initView();
    }

    private void initView() {
        SSJT.ROWSDETAILBean bean = ssjt.getROWS_DETAIL().get(0);
        busLine = bean.getSite();
        title.setText(bean.getId()+"路");
        tvBusLine.setText(busLine.get(0)+"-"+busLine.get(busLine.size()-1));
        tvTimeStart.setText(bean.getStart());
        tvTimeEnd.setText(bean.getEnd());
        allStation.setText(busLine.size()+"站/"+bean.getMileage()+"公里");
        price.setText("最高票价"+bean.getPrice()+"元");
        BusHistory busHistory = new BusHistory(title.getText()+"("+tvBusLine.getText()+")");
        busHistory.save();
        layout.removeAllViews();
        for (int i = 0; i < busLine.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.ssjt_item,null);
            TextView title_num = view.findViewById(R.id.title_num);
            TextView title_name = view.findViewById(R.id.title_name);
            if (i==0){
                title_num.setBackgroundResource(R.drawable.shap_yuan_rad);
                title_name.setTextColor(Color.RED);
            }else{
                title_num.setBackgroundResource(R.drawable.k_shap_yuan);
                title_name.setTextColor(Color.BLACK);
            }
            title_num.setText(i+1+"");
            title_name.setText(busLine.get(i));
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < layout.getChildCount(); j++) {
                        View view1 = layout.getChildAt(j);
                        TextView title_num = view1.findViewById(R.id.title_num);
                        TextView title_name = view1.findViewById(R.id.title_name);
                        if (finalI ==j){
                            title_name.setTextColor(Color.RED);
                            title_num.setBackgroundResource(R.drawable.shap_yuan_rad);
                        }else{
                            title_num.setBackgroundResource(R.drawable.k_shap_yuan);
                            title_name.setTextColor(Color.BLACK);
                        }

                    }
                }
            });
            view.setPadding(0,10,0,10);
            layout.addView(view,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().postSticky(new BusHistory("aaa"));
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
