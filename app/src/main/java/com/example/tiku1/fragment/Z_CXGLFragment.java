package com.example.tiku1.fragment;

import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.tiku1.R;
import com.example.tiku1.util.SimpDate;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-10 at 15:19 ：）
 */
public class Z_CXGLFragment extends BaseFragment {

    @BindView(R.id.toady_date)
    TextView toadyDate;
    @BindView(R.id.today_info)
    TextView todayInfo;
    @BindView(R.id.car_tv_1)
    TextView carTv1;
    @BindView(R.id.car_sw_1)
    Switch carSw1;
    @BindView(R.id.car_tv_2)
    TextView carTv2;
    @BindView(R.id.car_sw_2)
    Switch carSw2;
    @BindView(R.id.car_tv_3)
    TextView carTv3;
    @BindView(R.id.car_sw_3)
    Switch carSw3;
    @BindView(R.id.gif_image)
    ImageView gifImage;
    Unbinder unbinder;
    private TimePickerView timePickerView;

    @Override
    protected String setMyTitle() {
        return "出行管理";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cxgl_layout;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        AnimationDrawable drawable = (AnimationDrawable) gifImage.getDrawable();
        drawable.start();
        addLisenter(carSw1,carTv1);
        addLisenter(carSw2,carTv2);
        addLisenter(carSw3,carTv3);
        setToadyInfo(new Date());
    }

    private void setToadyInfo(Date date){

        toadyDate.setText(SimpDate.format("yyyy年MM月dd日",new Date()));
        int day = Integer.parseInt(SimpDate.format("dd",date));
        if ( day%2==0){
            todayInfo.setText("双号出行车辆2");
            carTv2.setText("开");
            carSw2.setChecked(true);
            carTv1.setText("关");
            carTv3.setText("关");
            carSw1.setChecked(false);
            carSw3.setChecked(false);
        }else{
            todayInfo.setText("单号出行车辆1、3");
            carTv2.setText("关");
            carSw2.setChecked(false);
            carTv1.setText("开");
            carTv3.setText("开");
            carSw1.setChecked(true);
            carSw3.setChecked(true);
        }
    }

    private void addLisenter(Switch carSw, final TextView carTv) {
        carSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                carTv.setText(isChecked?"开":"关");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.toady_date, R.id.today_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toady_date:
                timePickerView = new TimePickerBuilder(_mActivity, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        setToadyInfo(date);
                    }
                }).isDialog(true).build();
                timePickerView.show();
                break;
            case R.id.today_info:
                break;
        }
    }
}
