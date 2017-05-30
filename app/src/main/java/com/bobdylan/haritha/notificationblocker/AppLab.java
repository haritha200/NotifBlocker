package com.bobdylan.haritha.notificationblocker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AppLab {

    private static AppLab sAppLab;
    private ArrayList<AppType> mApps;

    public static AppLab get(Context context) {
        if(sAppLab==null){
            sAppLab=new AppLab(context);
        }
        return sAppLab;
    }

    private AppLab(Context context) {
        mApps = new ArrayList<AppType>();

        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(0);
        for(ApplicationInfo app : apps) {
            //checks for flags; if flagged, check if updated system app
          if(((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) || app.packageName.equals("com.android.mms")) {
                String label = (String)pm.getApplicationLabel(app);
                Drawable icon = pm.getApplicationIcon(app);
                String packname= app.packageName;
                AppType appType= new AppType();
                appType.setIcon(icon);
                appType.setLabel(label);
                appType.setPkgname(packname);
                mApps.add(appType);

                //it's a system app, not interested
           } else if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                //Discard this one

                //in this case, it should be a user-installed app
          } else {
                String label = (String)pm.getApplicationLabel(app);
                Drawable icon = pm.getApplicationIcon(app);
                String packname= app.packageName;
                AppType appType= new AppType();
                appType.setIcon(icon);
                appType.setLabel(label);
                appType.setPkgname(packname);
                mApps.add(appType);
            }
        }
        Collections.sort(mApps);

    }

    public List<AppType> getApps() {  //return as a List, so we can change or datastructure to Linkedlist or something if we need to in the future
        return mApps;
    }

    public AppType getApp(String pkg){
        for (AppType a: mApps) {
            if(a.getPkgname().equals(pkg))
                return  a;

        }
        return null;
    }
}
