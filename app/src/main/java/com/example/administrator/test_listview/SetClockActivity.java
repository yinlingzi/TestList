package com.example.administrator.test_listview;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SetClockActivity extends AppCompatActivity {
    private Button mBtnSet;
    private EditText mEditTextTime;
    private Switch mSwitch;
    private Calendar calendar ;
    private static final String TAG="Alarm";
    private boolean mclockState;
    private  static final String SWITCHSTATE="com.main.clock_state";
    private static  final  String RETURNSWITCHSTATE="com.setclock.clock_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_clock);
        mclockState=getIntent().getBooleanExtra(SWITCHSTATE,false);
        init();

    }
    private  void init(){

        calendar = Calendar.getInstance();
        mBtnSet=(Button)findViewById(R.id.clock_btn_set);
        //mBtnFinsh=(Button)findViewById(R.id.clock_btn_finish);
        mEditTextTime=(EditText)findViewById(R.id.clock_editext_time);
        mSwitch=(Switch)findViewById(R.id.clock_swith);
        mBtnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setTimeInMillis(System.currentTimeMillis());
                new TimePickerDialog(SetClockActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker arg0, int h, int m) {
                        //更新按钮上的时间
                        //timeBtn.setText(formatTime(h,m));
                        mEditTextTime.setText(formatTime(h,m));
                        //设置日历的时间，主要是让日历的年月日和当前同步
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        //设置日历的小时和分钟
                        calendar.set(Calendar.HOUR_OF_DAY, h);
                        calendar.set(Calendar.MINUTE, m);
                        //将秒和毫秒设置为0
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        //建立Intent和PendingIntent来调用闹钟管理器
                        Intent intent = new Intent(SetClockActivity.this,AlarmReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(SetClockActivity.this, 0, intent, 0);
                        //获取闹钟管理器
                        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                        //设置闹钟
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10*1000, pendingIntent);
                        Toast.makeText(SetClockActivity.this, "设置闹钟的时间为："+String.valueOf(h)+":"+String.valueOf(m), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "set the time to "+formatTime(h,m));
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();

            }
        });

//        mBtnFinsh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(SetClockActivity.this,AlarmReceiver.class);
////                PendingIntent pendingIntent = PendingIntent.getBroadcast(SetClockActivity.this, 0, intent, 0);
////                //获取闹钟管理器
////                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
////                alarmManager.cancel(pendingIntent);
//                Toast.makeText(SetClockActivity.this, "闹钟已经完成！", Toast.LENGTH_SHORT).show();
//
//            }
//        });
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                     mclockState = true;
                    Toast.makeText(SetClockActivity.this, "闹钟已经打开！", Toast.LENGTH_SHORT).show();
                 }
                else{
                    mclockState=false;
                    Intent intent = new Intent(SetClockActivity.this,AlarmReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(SetClockActivity.this, 0, intent, 0);
                    //获取闹钟管理器
                    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(SetClockActivity.this, "闹钟已经关闭！", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }



    public String formatTime(int h , int m) {
        StringBuffer buf = new StringBuffer();
        if(h<10){
            buf.append("0"+h);
        }else {
            buf.append(h);
        }
        buf.append(" : ");
        if(m<10){
            buf.append("0"+m);
        }else {
            buf.append(m);
        }
        return buf.toString();
    }
    public static Intent newIntent(Context packageContext, boolean clockState){
        Intent intent=new Intent(packageContext,SetClockActivity.class);
        intent.putExtra(SWITCHSTATE,clockState);
        return  intent;
    }
    //还没写完，设置闹铃的问题应该要放在缓存里面，不然的话就不晓得了吧


    private void setClockResult(boolean result){
        Intent data=new Intent();
        data.putExtra(RETURNSWITCHSTATE,result);
        setResult(RESULT_OK,data);
    }


}


