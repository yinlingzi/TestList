package com.example.administrator.test_listview;

/**
 * Created by Administrator on 2016/7/17.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context arg0, Intent data) {
       // Log.d(SetClockActivity.TAG, "the time is up,start the alarm...");
        Toast.makeText(arg0, "闹钟时间到了！", Toast.LENGTH_LONG).show();
    }
}
