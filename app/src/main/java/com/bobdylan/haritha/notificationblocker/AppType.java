package com.bobdylan.haritha.notificationblocker;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by haritha on 28/5/17.
 */

public class AppType implements Comparable<AppType>{

    private String label;
    private Drawable icon;
    private String pkgname;
    private UUID mId;
    private boolean mChecked;
    private int mCount;
    private List<String> mDates;

    public List<String> getDates() {
        return mDates;
    }

    public void addDate(String d){
        mDates.add(d);
    }

    public int getCount() {
        return mCount;

    }

    public void setCount(int count) {
        mCount = count;
    }

    AppType(){
        mId=UUID.randomUUID();
        mChecked=false;
        mCount=0;
        mDates=new ArrayList<String>();

    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
    }

    public UUID getId() {
        return mId;
    }

    public String getPkgname() {

        return pkgname;
    }

    public void setPkgname(String pkgname) {
        this.pkgname = pkgname;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int compareTo(AppType o) {
        return this.label.compareTo(o.label);
    }
}
