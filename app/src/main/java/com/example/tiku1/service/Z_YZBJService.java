package com.example.tiku1.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.android.volley.VolleyError;
import com.example.tiku1.R;
import com.example.tiku1.bean.YZ;
import com.example.tiku1.net.VolleyLo;
import com.example.tiku1.net.VolleyTo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-10 at 14:53 ：）
 */
public class Z_YZBJService extends Service {
    private YZ yz;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private VolleyTo volleyTo;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        setVolley();
        return super.onStartCommand(intent, flags, startId);
    }

    private void setVolley() {
        volleyTo = new VolleyTo();
        volleyTo.setUrl("get_threshold")
                .setJsonObject("UserName", "user1")
                .setLoop(true)
                .setTime(10000)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        yz = new Gson().fromJson(jsonArray.optJSONObject(0).toString(), YZ.class);
                       setVolley2();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setVolley2() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_road_status")
                .setJsonObject("UserName","user1")
                .setJsonObject("RoadId","1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        setVolley_sense(jsonArray.optJSONObject(0).optInt("state"));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_sense(final int state) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optInt("temperature")>yz.getTemperature()){
                            sendNotif(1,"温度报警，阈值"+yz.getTemperature()+"" +
                                    "，当前值"+jsonObject.optInt("temperature"));
                        }
                        if (jsonObject.optInt("humidity")>yz.getHumidity()){
                            sendNotif(2,"湿度报警，阈值"+yz.getHumidity()+"" +
                                    "，当前值"+jsonObject.optInt("humidity"));
                        }
                        if (jsonObject.optInt("illumination")>yz.getIllumination()){
                            sendNotif(3,"光照报警，阈值"+yz.getIllumination()+"" +
                                    "，当前值"+jsonObject.optInt("illumination"));
                        }
                        if (jsonObject.optInt("co2")>yz.getCo2()){
                            sendNotif(4,"CO2报警，阈值"+yz.getCo2()+"" +
                                    "，当前值"+jsonObject.optInt("co2"));
                        }
                        if (jsonObject.optInt("pm25")>yz.getPm25()){
                            sendNotif(5,"PM2.5报警，阈值"+yz.getPm25()+"" +
                                    "，当前值"+jsonObject.optInt("pm25"));
                        }
                        if (state>yz.getPath()){
                            sendNotif(5,"PM2.5报警，阈值"+yz.getPath()+"" +
                                    "，当前值"+state);
                        }

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void sendNotif(int id,String msg){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_environment)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("空气指标")
                .setContentText(msg);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id,builder.build());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        volleyTo.setLoop( false);
        volleyTo = null;
    }
}
