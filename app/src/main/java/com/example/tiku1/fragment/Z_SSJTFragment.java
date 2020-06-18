package com.example.tiku1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku1.R;
import com.example.tiku1.activity.Z_SSJTJGActivity;
import com.example.tiku1.adapter.BusAdapter;
import com.example.tiku1.bean.BusHistory;
import com.example.tiku1.bean.SSJT;
import com.example.tiku1.net.VolleyLo;
import com.example.tiku1.net.VolleyTo;
import com.example.tiku1.util.ShowDialog;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-12 at 16:21 ：）
 */
public class Z_SSJTFragment extends BaseFragment {
    @BindView(R.id.et_bus)
    EditText etBus;
    @BindView(R.id.layout_search)
    LinearLayout layoutSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.history_list)
    ListView historyList;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    Unbinder unbinder;
    private SSJT ssjt;
    private List<BusHistory> busHistories;
    private BusAdapter adapter;

    @Override
    protected String setMyTitle() {
        return "实时交通";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ssjt_layout;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        busHistories = LitePal.findAll(BusHistory.class);
        adapter = new BusAdapter(_mActivity,busHistories);
        historyList.setAdapter(adapter);

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

    @OnClick({R.id.tv_search, R.id.tv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                int busId = Integer.parseInt(etBus.getText().toString());
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("get_bus_data")
                        .setJsonObject("UserName","user1")
                        .setJsonObject("Busid",busId)
                        .setDialog(_mActivity)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                ssjt = new Gson().fromJson(jsonObject.toString(),SSJT.class);
                               if (ssjt.getROWS_DETAIL().size()==0){
                                    ShowDialog.ShowMyDialog(_mActivity,"没有查询到此线路");
                                }else{
                                    Intent intent = new Intent(_mActivity, Z_SSJTJGActivity.class);
                                    intent.putExtra("info",ssjt);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();
                break;
            case R.id.tv_clear:
                LitePal.deleteAll(BusHistory.class);
                busHistories.clear();
                busHistories = LitePal.findAll(BusHistory.class);
                adapter.notifyDataSetChanged();

                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void setBusHistories(BusHistory  msg) {
        busHistories.clear();
        busHistories = LitePal.findAll(BusHistory.class);
        adapter.notifyDataSetChanged();
    }
}
