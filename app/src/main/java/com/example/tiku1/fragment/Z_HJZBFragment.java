package com.example.tiku1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.example.tiku1.AppClient;
import com.example.tiku1.R;
import com.example.tiku1.activity.Z_SSXSActivity;
import com.example.tiku1.adapter.HJZBAdapter;
import com.example.tiku1.bean.YZ;
import com.example.tiku1.net.VolleyLo;
import com.example.tiku1.net.VolleyTo;
import com.example.tiku1.util.SimpDate;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-06-05 ：）
 */
public class Z_HJZBFragment extends BaseFragment {
    @BindView(R.id.my_gird_view)
    GridView myGirdView;
    Unbinder unbinder;
    private YZ yz,sense;
    private HJZBAdapter adapter;
    private List<YZ> yzs;
    private AppClient appClient;
    private VolleyTo volleyTo;



    @Override
    protected String setMyTitle() {
        return "环境指标";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.hjzb_layout;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        appClient = (AppClient) getActivity().getApplication();
        yzs = appClient.getYzs();
        setVolley_Yz();
        myGirdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(_mActivity, Z_SSXSActivity.class);
                intent.putExtra("index",position);
                startActivity(intent);
            }
        });
    }

    private void setVolley_Yz() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_threshold")
                .setJsonObject("UserName","user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        yz = new Gson().fromJson(jsonArray.optJSONObject(0).toString(),YZ.class);
                        setVolley_Sense();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_Sense() {
        volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setLoop(true)
                .setTime(3000)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        sense = new Gson().fromJson(jsonObject.toString(),YZ.class);
                        setVolley_Dl();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_Dl() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_road_status")
                .setJsonObject("UserName","user1")
                .setJsonObject("RoadId","1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        sense.setPath(jsonObject1.optInt("state"));
                        sense.setTime(SimpDate.format("mm:ss",new Date()));
                        yzs.add(sense);
                        if (yzs.size()==21){
                            yzs.remove(0);
                        }
                        setAdapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setAdapter() {
        if (adapter==null){
            adapter = new HJZBAdapter(_mActivity,R.layout.hjzb_item,yzs,yz);
            myGirdView.setAdapter(adapter);
        }
        else{
            adapter.notifyDataSetChanged();
        }
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
        volleyTo.setLoop( false);
        volleyTo = null;
        unbinder.unbind();
    }
}
