package com.example.administrator.test_listview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.tencent.wstt.gt.client.AbsGTParaLoader;
import com.tencent.wstt.gt.client.GT;
import com.tencent.wstt.gt.client.InParaManager;
import com.tencent.wstt.gt.client.OutParaManager;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Item>> iData = null;
    private ArrayList<Item> lData = null;
    private Context mContext;
    private ExpandableListView exlist_lol;
    private MyBaseExpandableListAdapter myAdapter = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        exlist_lol = (ExpandableListView) findViewById(R.id.exlist_lol);


        //数据准备
        gData = new ArrayList<Group>();
        iData = new ArrayList<ArrayList<Item>>();
        gData.add(new Group("今天"));
        gData.add(new Group("明天"));
        gData.add(new Group("以后"));

        lData = new ArrayList<Item>();

//        //今天组
//        lData.add(new Item("打球","12:00","个人事务"));
//        iData.add(lData);
//        //明天组
//        lData = new ArrayList<Item>();
//        lData.add(new Item("看书","12:00","个人事务"));
//        iData.add(lData);
//        //未来组
//        lData = new ArrayList<Item>();
//        lData.add(new Item("上网","12:00","个人事务"));
//        iData.add(lData);

        myAdapter = new MyBaseExpandableListAdapter(gData,iData,mContext);
        exlist_lol.setAdapter(myAdapter);


        //

        //为列表设置点击事件
        exlist_lol.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, "你点击了：" + iData.get(groupPosition).get(childPosition).getiName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

    public static double div(double d1, double d2, int scale) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.divide(bd2, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }
}
