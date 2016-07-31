package com.example.administrator.test_listview;

import android.app.Activity;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/31.
 */
public class SetTarget extends AppCompatActivity {
    private EditText mTextViewTitle,mTextViewContent;
    private RadioGroup mRadioGroup;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_target);
        init();



    }

    private  void init(){
        mButton=(Button)findViewById(R.id.setting_target_saved_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetTarget.this,"you click it",Toast.LENGTH_SHORT).show();
            }
        });
        mRadioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                Toast.makeText(getApplicationContext(), "按钮组值发生改变,你选了" + radbtn.getText(), Toast.LENGTH_LONG).show();
            }
        });


        mTextViewContent=(EditText) findViewById(R.id.setting_target_content_text);
        mTextViewTitle=(EditText)findViewById(R.id.setting_target_title_text);

    }



}

