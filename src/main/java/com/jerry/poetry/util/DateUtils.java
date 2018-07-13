package com.jerry.poetry.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getDay(long time){
        Date date = new Date(time);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
