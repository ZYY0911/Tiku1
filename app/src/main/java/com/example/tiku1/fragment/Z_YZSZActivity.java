package com.example.tiku1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku1.AppClient;
import com.example.tiku1.R;
import com.example.tiku1.bean.YZ;
import com.example.tiku1.net.VolleyLo;
import com.example.tiku1.net.VolleyTo;
import com.example.tiku1.service.Z_YZBJService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-06-06 ：）
 */
public class Z_YZSZActivity extends BaseFragment {
    @BindView(R.id.tv_switch)
    TextView tvSwitch;
    @BindView(R.id.my_switch)
    Switch mySwitch;
    @BindView(R.id.et_wd)
    EditText etWd;
    @BindView(R.id.et_sd)
    EditText etSd;
    @BindView(R.id.et_gz)
    EditText etGz;
    @BindView(R.id.et_co)
    EditText etCo;
    @BindView(R.id.et_pm)
    EditText etPm;
    @BindView(R.id.et_dl)
    EditText etDl;
    @BindView(R.id.bt_save)
    Button btSave;
    Unbinder unbinder;
    private AppClient appClient;
    private YZ yz;
    private Intent intent;
    @Override
    protected String setMyTitle() {
        return "阈值设置";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.yzsz_layout;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        intent = new Intent(_mActivity, Z_YZBJService.class);
        appClient = (AppClient) getActivity().getApplication();
        setVolley();
        initLisent();
        mySwitch.setChecked(appClient.getAutoBj());
        tvSwitch.setText(appClient.getAutoBj()?"开":"关");
    }

    private void initLisent() {
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    getActivity().startService(intent);
                }else{
                    getActivity().stopService(intent);
                }
                tvSwitch.setText(isChecked?"开":"关");
                etCo.setEnabled(!isChecked);
                etDl.setEnabled(!isChecked);
                etGz.setEnabled(!isChecked);
                etPm.setEnabled(!isChecked);
                etSd.setEnabled(!isChecked);
                etWd.setEnabled(!isChecked);
                btSave.setEnabled(!isChecked);
                appClient.setAutoBj(isChecked);
            }
        });
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_threshold")
                .setJsonObject("UserName", "user1")
                .setDialog(_mActivity)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        yz = new Gson().fromJson(jsonArray.optJSONObject(0).toString(), YZ.class);
                        etWd.setText(yz.getTemperature() + "");
                        etSd.setText(yz.getHumidity() + "");
                        etCo.setText(yz.getCo2() + "");
                        etPm.setText(yz.getPm25() + "");
                        etDl.setText(yz.getPath() + "");
                        etGz.setText(yz.getIllumination() + "");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
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

    @OnClick(R.id.bt_save)
    public void onViewClicked() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("set_threshold")
                .setJsonObject("UserName","user1")
                //{"UserName":"user1","temperature":"30","humidity":"15","illumination":"100",
                // "co2":"1000","pm25":"100","path":"7"}
                .setJsonObject("temperature",etWd.getText())
                .setJsonObject("humidity",etSd.getText())
                .setJsonObject("illumination",etGz.getText())
                .setJsonObject("co2",etCo.getText())
                .setJsonObject("pm25",etPm.getText())
                .setJsonObject("path",etDl.getText())
                .setDialog(_mActivity)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }
}
