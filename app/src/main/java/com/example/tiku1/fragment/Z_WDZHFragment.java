package com.example.tiku1.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.tiku1.AppClient;
import com.example.tiku1.R;
import com.example.tiku1.activity.MainActivity;
import com.example.tiku1.bean.CZSql;
import com.example.tiku1.dialog.Z_CzDialog;
import com.example.tiku1.net.VolleyLo;
import com.example.tiku1.net.VolleyTo;
import com.example.tiku1.util.ShowDialog;
import com.example.tiku1.util.SimpDate;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Create by 张瀛煜 on 2020-06-04 ：）
 */
public class Z_WDZHFragment extends BaseFragment {

    @BindView(R.id.tv_ye)
    TextView tvYe;
    @BindView(R.id.sp_ch)
    Spinner spCh;
    @BindView(R.id.et_add)
    EditText etAdd;
    @BindView(R.id.bt_query)
    Button btQuery;
    @BindView(R.id.bt_recharge)
    Button btRecharge;
    Unbinder unbinder;
    private AppClient appClient;

    @Override
    protected int getLayoutId() {
        return R.layout.wdzh_fragment;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        appClient = (AppClient) getmActivity().getApplication();
        setVolley(spCh.getSelectedItem().toString());
        initListen();

    }



    private void initListen() {
        spCh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setVolley(spCh.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        etAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")){
                    if (Integer.parseInt(s.toString())>999){
                        etAdd.setText("999");
                        etAdd.setSelection(3);
                        Toast.makeText(_mActivity, "只能输入1～999", Toast.LENGTH_SHORT).show();
                    }
                    if (s.toString().equals("0")){
                        etAdd.setText("");
                        Toast.makeText(_mActivity, "不能输入0", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void setVolley(String carNum) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_balance_b")
                .setJsonObject("UserName","user1")
                .setJsonObject("number",carNum)
                .setDialog(_mActivity)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tvYe.setText(jsonObject.optString("balance")+"元");
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
    protected String setMyTitle() {
        return "我的账户";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_query, R.id.bt_recharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_query:
                extraTransaction().startDontHideSelf(new Z_ZDGLFragment());
                break;
            case R.id.bt_recharge:
                if ("".equals(etAdd.getText().toString())){
                    ShowDialog.ShowMyDialog(_mActivity,"请输入充值金额");
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(_mActivity);
                builder.setTitle("提示");
                builder.setMessage("您确定要在"+SimpDate.format("yyyy年MM月dd日HH:mm:ss",new Date())+"给" +
                        spCh.getSelectedItem().toString()+"号小车充值"+etAdd.getText().toString()+"元吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Recharge(spCh.getSelectedItem().toString(), Integer.parseInt(etAdd.getText().toString()));
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        if (appClient.ShowCz()) {
                            Z_CzDialog z_czDialog = new Z_CzDialog();
                            z_czDialog.setSureAndCancel(new Z_CzDialog.SureAndCancel() {
                                @Override
                                public void DialogResult(boolean is) {
                                    if (is) {
                                        Recharge(spCh.getSelectedItem().toString(), Integer.parseInt(etAdd.getText().toString()));
                                    } else {
                                        dialog.dismiss();
                                        etAdd.setText("");
                                    }
                                }
                            });
                            z_czDialog.show(getChildFragmentManager(), "");
                        }
                    }
                });
                builder.create().show();

                break;
        }
    }

    /**
     *
     * @param carNum 车辆编号
     * @param money 充值金额
     */
    private void Recharge(final String carNum, final int money){
        final VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_plate")
                .setJsonObject("UserName","user1")
                .setJsonObject("number",carNum)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String carPail = jsonObject.optString("plate");
                        VolleyTo volleyTo1 = new VolleyTo();
                        volleyTo1.setUrl("set_balance")
                                .setJsonObject("UserName","user1")
                                .setJsonObject("plate",carPail)
                                .setDialog(_mActivity)
                                .setJsonObject("balance",money)
                                .setVolleyLo(new VolleyLo() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        setVolley(carNum);
                                        ShowDialog.ShowMyDialog(_mActivity,"充值成功");
                                        new CZSql(carNum,money+"", AppClient.getLogName()
                                                , SimpDate.format("yyyy.MM.dd HH:mm",new Date())).save();
                                        etAdd.setText("");
                                    }

                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {

                                    }
                                }).start();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
}
