package com.example.tiku1.bean;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-11 at 20:40 ：）
 */
public class CCRQ {
    private int position;
    private String msg;

    public CCRQ(int position, String msg) {
        this.position = position;
        this.msg = msg;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
