package com.example.tiku1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku1.AppClient;
import com.example.tiku1.R;
import com.example.tiku1.bean.LogInBean;
import com.example.tiku1.net.VolleyLo;
import com.example.tiku1.net.VolleyTo;
import com.example.tiku1.util.ShowDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-06-04 ：）
 */
public class Z_LogInActivity extends AppCompatActivity {


    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.left_title)
    LinearLayout leftTitle;
    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.textInputLayoutName)
    TextInputLayout textInputLayoutName;
    @BindView(R.id.editPassWord)
    EditText editPassWord;
    @BindView(R.id.textInputLayoutPassWord)
    TextInputLayout textInputLayoutPassWord;
    @BindView(R.id.cb_remember)
    CheckBox cbRemember;
    @BindView(R.id.cb_auto)
    CheckBox cbAuto;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_register)
    Button btRegister;
    private List<LogInBean> logInBeans;
private AppClient appClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        initView();
        initVolley();


    }

    private void initVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_login")
                .setJsonObject("UserName","user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        logInBeans = new Gson().fromJson(jsonArray.toString(),
                                new TypeToken<List<LogInBean>>(){}.getType());
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        ShowDialog.ShowMyDialog(Z_LogInActivity.this,"请检查您的网络设置！");
                    }
                }).start();
    }

    private void initView() {
        title.setText("用户登录");
        title2.setText("网络设置");
        appClient = (AppClient) getApplication();

    }

    @OnClick({R.id.change, R.id.left_title, R.id.bt_login, R.id.bt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.left_title:

                break;
            case R.id.bt_login:
                String userName = editTextName.getText().toString();
                String passWord = editPassWord.getText().toString();
                if ("".equals(userName)||"".equals(passWord)){
                    ShowDialog.ShowMyDialog(this,"用户名和密码不能为空");
                }else{
                    for (int i = 0; i < logInBeans.size(); i++) {
                        LogInBean logInBean = logInBeans.get(i);
                        if (logInBean.getUsername().equals(userName)&&logInBean.getPassword().equals(passWord)){
                            appClient.setAutoLog(cbAuto.isChecked());
                            appClient.setARemember(cbRemember.isChecked());
                            if (cbRemember.isChecked()){
                                appClient.setUserName(userName);
                                appClient.setPassWord(passWord);
                            }else{
                                appClient.setPassWord("");
                                appClient.setUserName("");
                            }
                            appClient.setUserName(userName);
                            startActivity(new Intent(Z_LogInActivity.this,MainActivity.class));
                            finish();
                            return;
                        }
                    }
                    ShowDialog.ShowMyDialog(this,"用户名或密码不正确");
                }
                break;
            case R.id.bt_register:
                break;
        }
    }
}
