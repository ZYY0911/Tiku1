package com.example.tiku1.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by 张瀛煜 on 2020-06-04 ：）
 */
public class SimpDate {

    public static String format(String type, Date date){
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }


}
