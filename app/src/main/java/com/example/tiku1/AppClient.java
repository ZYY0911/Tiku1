package com.example.tiku1;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tiku1.bean.YZ;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 张瀛煜 on 2020-06-02 ：）
 */
public class AppClient extends Application {

    private static RequestQueue requestQueue;

    private static SharedPreferences preferences;
    public static final String IsFirst = "LogIn";
    private static String LogName;
    private List<YZ> yzs = new ArrayList<>();

    public List<YZ> getYzs() {
        return yzs;
    }

    private final String YJ = "HjBj";

    public void setAutoBj(boolean auto){
        preferences.edit().putBoolean(YJ,auto).apply();
    }

    public boolean getAutoBj(){
        return preferences.getBoolean(YJ,false);
    }

    public static String getLogName() {
        return LogName;
    }

    public static void setLogName(String logName) {
        LogName = logName;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        requestQueue = Volley.newRequestQueue(this);
    }


    public static boolean getIsFirst() {
        return preferences.getBoolean(IsFirst, false);
    }


    public static void setIsFirst(boolean is) {
        preferences.edit().putBoolean(IsFirst, is).apply();
    }

    private final String UserName = "username";
    private final String PassWord = "password";
    private final String AutoLog = "autolog";
    private final String RemamberMe = "rememberme";

    public String getUserName() {
        return preferences.getString(UserName, "");
    }

    public void setUserName(String userName) {
        preferences.edit().putString(UserName, userName).apply();
    }

    public String getPassWord() {
        return preferences.getString(PassWord, "");
    }

    public void setPassWord(String password) {
        preferences.edit().putString(PassWord, password).apply();
    }

    public boolean getAutoLog() {
        return preferences.getBoolean(AutoLog, false);
    }

    public void setAutoLog(boolean auto) {
        preferences.edit().putBoolean(AutoLog, auto).apply();
    }

    public boolean getRemember() {
        return preferences.getBoolean(RemamberMe, false);
    }

    public void setARemember(boolean remember) {
        preferences.edit().putBoolean(RemamberMe, remember).apply();
    }

    private final String ShowCzDialog = "showczdialog";

    public boolean ShowCz() {
        return preferences.getBoolean(ShowCzDialog, true);
    }

    public void SetShowCz(boolean is) {
        preferences.edit().putBoolean(ShowCzDialog, is).apply();
    }

    public static void add(JsonObjectRequest jsonObjectRequest) {
        requestQueue.add(jsonObjectRequest);
    }

    private static List<Activity>activities = new ArrayList<>();

    public  static  void addActivity(Activity activity){
        activities.add(activity);
    }

    public static  void finaAll(){
        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
