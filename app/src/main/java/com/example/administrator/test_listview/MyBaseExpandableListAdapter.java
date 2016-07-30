package com.example.administrator.test_listview;

/**
 * Created by Administrator on 2016/7/11.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.test_listview.MainActivity;

import java.util.ArrayList;

/**
 * Created by Jay on 2015/9/25 0025.
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter  {


    boolean clockState=false;

    private ArrayList<Group> gData;
    private ArrayList<ArrayList<Item>> iData;
    private Context mContext;

    public MyBaseExpandableListAdapter(ArrayList<Group> gData,ArrayList<ArrayList<Item>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public Group getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Item getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_exlist_group, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_group_name);
            groupHolder.tv_group_button_add=(ImageButton)convertView.findViewById(R.id.add_task);

            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition).getgName());
        groupHolder.tv_group_button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "你点击了增加" , Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_exlist_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.img_btn = (ImageButton) convertView.findViewById(R.id.sub_button_clock_id);
            itemHolder.tv_name = (TextView) convertView.findViewById(R.id.sub_text_id);
            itemHolder.tv_attr=(TextView)convertView.findViewById(R.id.sub_text_attr);
            itemHolder.tv_tiem=(TextView)convertView.findViewById(R.id.sub_text_time);
            convertView.setTag(itemHolder);
        }else{
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
       // itemHolder.img_icon.setImageResource(iData.get(groupPosition).get(childPosition).getiId());
        itemHolder.img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clockState=false;//从图片获取状态
                Intent clockset=SetClockActivity.newIntent(mContext,clockState);
                mContext.startActivity(clockset);

                //Toast.makeText(mContext, "你点击了设置闹铃" , Toast.LENGTH_SHORT).show();


            }
        });
        itemHolder.tv_name.setText(iData.get(groupPosition).get(childPosition).getiName());
        itemHolder.tv_tiem.setText(iData.get(groupPosition).get(childPosition).getiTime());
        itemHolder.tv_attr.setText(iData.get(groupPosition).get(childPosition).getiAttr());
        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private static class ViewHolderGroup{
        private TextView tv_group_name;
        private ImageButton tv_group_button_add;
    }

    private static class ViewHolderItem{
        //private ImageView img_icon;
        private ImageButton img_btn;
        private TextView tv_name;
        private TextView tv_tiem;
        private  TextView tv_attr;
    }





}
