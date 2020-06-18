package com.example.tiku1.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Create by 张瀛煜 on 2020-06-04 ：）
 */
public class ShowDialog {
    public static void ShowMyDialog(Context context, String msg){
        AlertDialog.Builder  builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(msg);
        builder.setPositiveButton("确定", null);
        builder.setNegativeButton("取消",null);
        builder.create().show();
    }
}
