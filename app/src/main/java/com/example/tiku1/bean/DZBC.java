package com.example.tiku1.bean;

import java.io.Serializable;
import java.util.List;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-11 at 15:22 ：）
 */
public class DZBC  implements Serializable {

    /**
     * id : 1
     * busline : ["光谷金融街","戎军南路","长河公园","南湖商厦"]
     * fares : 8
     * mileage : 20
     * time : 06:45~19:45
     */

    private int id;
    private int fares;
    private int mileage;
    private String time;
    private List<String> busline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFares() {
        return fares;
    }

    public void setFares(int fares) {
        this.fares = fares;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getBusline() {
        return busline;
    }

    public void setBusline(List<String> busline) {
        this.busline = busline;
    }
}
