package com.example.tiku1.fragment;

import android.os.Bundle;

import com.example.tiku1.R;

/**
 * Create by 张瀛煜 on 2020-06-05 ：）
 */
public class HomeFragment extends BaseFragment {
    @Override
    protected String setMyTitle() {
        return "主页面";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_layout;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
