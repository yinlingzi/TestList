package com.example.administrator.test_listview;

/**
 * Created by Administrator on 2016/7/11.
 */
public class Item {

    //private int iId;
    private String iName;
    private  String iTime;
    private  String iAttr;

    public Item() {
    }

    public Item( String iName, String iTime, String iAttr) {
        //this.iId = iId;
        this.iName = iName;
        this.iTime = iTime;
        this.iAttr = iAttr;
    }

   /* public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }*/

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getiTime() {
        return iTime;
    }

    public void setiTime(String iTime) {
        this.iTime = iTime;
    }

    public String getiAttr() {
        return iAttr;
    }

    public void setiAttr(String iAttr) {
        this.iAttr = iAttr;
    }
}
