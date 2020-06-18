package com.example.tiku1.bean;

/**
 * Create by 张瀛煜 on 2020-06-05 ：）
 */
public class YZ {

    /**
     * temperature : 20
     * humidity : 10
     * illumination : 1000
     * co2 : 2000
     * pm25 : 50
     * path : 3
     */

    private int temperature;
    private int humidity;
    private int illumination;
    private int co2;
    private int pm25;
    private int path;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getIllumination() {
        return illumination;
    }

    public void setIllumination(int illumination) {
        this.illumination = illumination;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
