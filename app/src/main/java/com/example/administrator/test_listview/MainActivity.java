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

        //AD组
        lData.add(new Item("打球","12:00","个人事务"));
        iData.add(lData);
        //AP组
        lData = new ArrayList<Item>();
        lData.add(new Item("看书","12:00","个人事务"));
        iData.add(lData);
        //TANK组
        lData = new ArrayList<Item>();
        lData.add(new Item("上网","12:00","个人事务"));
        iData.add(lData);

        myAdapter = new MyBaseExpandableListAdapter(gData,iData,mContext);
        exlist_lol.setAdapter(myAdapter);


        //

        //为列表设置点击事件
        exlist_lol.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            long startTime=0; // 本次下载图片的开始时间
            long endTime=0; // 本次下载完所有图片的结束时
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //if(startTime==0)
                    startTime=System.nanoTime();
                Toast.makeText(mContext, "你点击了：" + iData.get(groupPosition).get(childPosition).getiName(), Toast.LENGTH_SHORT).show();
                endTime=System.nanoTime();


                return true;
            }
        });


    }

    public static double div(double d1, double d2, int scale) {
        // 当然在此之前，你要判断分母是否为0，
        // 为0你可以根据实际需求做相应的处理

        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
//		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        // 直接向下取整，保持和UI展示一致
        return bd1.divide(bd2, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }
}
