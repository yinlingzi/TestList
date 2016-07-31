package com.example.administrator.test_listview;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/7/31.
 */
public class Target {
   private  String mTitle,mContent,mTime,msubstr;

    public Target() {
    }

    public Target(String title, String msubstr, String time, String content) {
        mTitle = title;
        this.msubstr = msubstr;
        mTime = time;
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getMsubstr() {
        return msubstr;
    }

    public void setMsubstr(String msubstr) {
        this.msubstr = msubstr;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");

        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        int begin=str.indexOf(' ');
        int end=str.lastIndexOf(':');
        mTime=str.substring(begin,end);
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
