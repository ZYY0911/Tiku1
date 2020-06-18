package com.example.tiku1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku1.R;
import com.example.tiku1.bean.WZBH;
import com.example.tiku1.bean.WZXX;
import com.example.tiku1.net.VolleyLo;
import com.example.tiku1.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-06-05 ：）
 */
public class Z_CXJGActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_road)
    TextView tvRoad;
    @BindView(R.id.iv_left_arrow)
    ImageView ivLeftArrow;
    @BindView(R.id.iv_image_photo)
    ImageView ivImagePhoto;
    @BindView(R.id.iv_image_video)
    ImageView ivImageVideo;
    @BindView(R.id.iv_right_arrow)
    ImageView ivRightArrow;
    @BindView(R.id.tv_wz)
    TextView tvWz;
    @BindView(R.id.tv_fk)
    TextView tvFk;
    @BindView(R.id.tv_kf)
    TextView tvKf;
    @BindView(R.id.tv_sj)
    TextView tvSj;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    private String Cp;
    private WZBH wzbh;
    private List<WZXX> wzxxes, wzxxes2, wzxxesall;
    private int index = 0;
    private int [] imagePhoto ={R.mipmap.weizhang1,R.mipmap.weizhang2,R.mipmap.weizhang3,R.mipmap.weizhang4
            ,R.mipmap.weizhang01,R.mipmap.weizhang02,R.mipmap.weizhang03,R.mipmap.weizhang04};
    private int [] imageVideo = {R.mipmap.videocar1,R.mipmap.videocar2,R.mipmap.videocar3,R.mipmap.videocar4};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cxjl_layout);
        ButterKnife.bind(this);
        initView();
        setVolley();
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_car_peccancy")
                .setJsonObject("UserName", "user1")
                .setDialog(this)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        wzxxes = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<WZXX>>() {
                        }.getType());
                        setVolley2();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley2() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_pessancy_infos")
                .setJsonObject("UserName", "user1")
                .setDialog(this)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        wzxxes2 = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<WZXX>>() {
                        }.getType());
                        setListInfos();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListInfos() {
        List<Integer> bhs = wzbh.getId();
        for (int i = 0; i < wzxxes.size(); i++) {
            WZXX wzxx = wzxxes.get(i);
            for (int j = 0; j < wzxxes2.size(); j++) {
                WZXX wzxx1 = wzxxes2.get(j);
                if (wzxx.getInfoid().equals(wzxx1.getInfoid())) {
                    wzxx.setRoad(wzxx1.getRoad());
                    wzxx.setDeduct(wzxx1.getDeduct());
                    wzxx.setMessage(wzxx1.getMessage());
                    wzxx.setFine(wzxx1.getFine());
                    for (int k = 0; k < bhs.size(); k++) {
                        if (wzxx.getId() == bhs.get(k)) {
                            wzxxesall.add(wzxx);
                        }
                    }
                }
            }
        }
        setViewText();
    }

    private void setViewText() {
        WZXX wzxx = wzxxesall.get(index);
        tvRoad.setText(wzxx.getRoad());
        tvMsg.setText(wzxx.getMessage());
        ivImagePhoto.setImageResource(imagePhoto[index]);
        ivImageVideo.setImageResource(imageVideo[index]);
        tvWz.setText(wzxxesall.size()+"");
        tvFk.setText(wzxx.getFine()+"");
        tvKf.setText(wzxx.getDeduct()+"");
        tvSj.setText(wzxx.getTime());
    }

    private void initView() {
        Cp = getIntent().getStringExtra("Cp");
        wzbh = (WZBH) getIntent().getSerializableExtra("date");
        wzxxesall = new ArrayList<>();
        title.setText("车辆违章");
    }

    @OnClick({R.id.change, R.id.iv_left_arrow, R.id.iv_image_photo, R.id.iv_image_video, R.id.iv_right_arrow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.iv_left_arrow:
                if (index==0){
                    return;
                }
                index--;
                setViewText();
                break;
            case R.id.iv_image_photo:
                Intent intent = new Intent(this,Z_WZTPActivity.class);
                intent.putExtra("Bh",index);
                startActivity(intent);
                break;
            case R.id.iv_image_video:
                Intent intent2 = new Intent(this,Z_WZSPActivity.class);
                intent2.putExtra("Bh",index);
                startActivity(intent2);
                break;
            case R.id.iv_right_arrow:
                if (index==wzxxesall.size()-1){
                    return;
                }index++;
                setViewText();
                break;
        }
    }
}
