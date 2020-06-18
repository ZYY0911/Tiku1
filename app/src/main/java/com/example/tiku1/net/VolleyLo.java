package com.example.tiku1.net;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Create by 张瀛煜 on 2020-06-02 ：）
 */
public interface VolleyLo {
    void onResponse(JSONObject jsonObject);
    void onErrorResponse(VolleyError volleyError);
}
