package com.example.tiku1.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku1.R;
import com.example.tiku1.activity.Z_DZBCActivity1;
import com.example.tiku1.bean.DZBC;
import com.example.tiku1.net.VolleyLo;
import com.example.tiku1.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-11 at 15:18 ：）
 */
public class Z_DZBCActivity extends BaseFragment {

    @BindView(R.id.my_list_view)
    ListView myListView;
    Unbinder unbinder;
    private List<DZBC> dzbcs;
    private MyDZbcAdatper adatper;

    @Override
    protected String setMyTitle() {
        return "定制班车";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dzbc_layout;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setVolley();
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_bus_info")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        dzbcs = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<DZBC>>() {
                                }.getType());
                        setMyListView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setMyListView() {
        adatper = new MyDZbcAdatper(_mActivity, dzbcs);
        myListView.setAdapter(adatper);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(_mActivity, Z_DZBCActivity1.class);
                intent.putExtra("info",dzbcs.get(position));
                startActivity(intent);
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

    static class MyDZbcAdatper extends ArrayAdapter<DZBC> {

        public MyDZbcAdatper(@NonNull Context context, @NonNull List<DZBC> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.dzbc_item, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            DZBC dzbc = getItem(position);
            holder.itemCh.setText(dzbc.getId() + "路");
            holder.itemInfo.setText("票价：¥" + dzbc.getFares() + "   里程:" + dzbc.getMileage() + "km");
            holder.itemXl.setText(dzbc.getBusline().get(0) + "--" + dzbc.getBusline().get(dzbc.getBusline().size() - 1));
            String arr[] = dzbc.getTime().split("~");
            holder.itemStartTime.setText(arr[0]);
            holder.itemEndTime.setText(arr[1]);
            return convertView;
        }

        static
        class ViewHolder {
            @BindView(R.id.item_ch)
            TextView itemCh;
            @BindView(R.id.item_xl)
            TextView itemXl;
            @BindView(R.id.item_info)
            TextView itemInfo;
            @BindView(R.id.item_start_time)
            TextView itemStartTime;
            @BindView(R.id.item_end_time)
            TextView itemEndTime;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
