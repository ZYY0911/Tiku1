package com.example.tiku1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.example.tiku1.R;
import com.example.tiku1.activity.Z_CXJGActivity;
import com.example.tiku1.bean.WZBH;
import com.example.tiku1.net.VolleyLo;
import com.example.tiku1.net.VolleyTo;
import com.example.tiku1.util.ShowDialog;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-06-04 ：）
 */
public class Z_CLWZFragemnt extends BaseFragment {
    @BindView(R.id.et_car)
    EditText etCar;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    @BindView(R.id.query)
    Button query;
    Unbinder unbinder;
    private WZBH wzbh;

    @Override
    protected String setMyTitle() {
        return "车辆违章";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.clwz_layout;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
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

    @OnClick(R.id.query)
    public void onViewClicked() {
        final String cp = etCar.getText().toString().toUpperCase();
        if ("" == cp || cp.length() != 6) {
            ShowDialog.ShowMyDialog(_mActivity, "请输入正确的车牌号");
        } else {
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("get_peccancy_plate")
                    .setJsonObject("UserName", "user1")
                    .setJsonObject("plate", "鲁" + cp)
                    .setDialog(_mActivity)
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            wzbh = new Gson().fromJson(jsonObject.toString(), WZBH.class);
                            if (wzbh.getId().size() == 0) {
                                ShowDialog.ShowMyDialog(_mActivity, "没有查询到鲁" + cp + "车的违章数据！");
                            } else {
                                Intent intent = new Intent(_mActivity, Z_CXJGActivity.class);
                                intent.putExtra("Cp", "鲁" + cp);
                                intent.putExtra("date",wzbh);
                                startActivity(intent);
                                etCar.setText("");
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();

        }

    }
}
