package com.example.tiku1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tiku1.AppClient;
import com.example.tiku1.R;

/**
 * Create by 张瀛煜 on 2020-06-02 ：）
 */
public class Z_FirstActivity extends AppCompatActivity {
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            startActivity(new Intent(Z_FirstActivity.this,Z_LogInActivity.class));
            finish();
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!AppClient.getIsFirst()){
            setContentView(R.layout.first_layout);
            AppClient.setIsFirst(true);
            handler.sendEmptyMessageDelayed(0,2000);
        }else{
            handler.sendEmptyMessage(0);
        }
    }


}
