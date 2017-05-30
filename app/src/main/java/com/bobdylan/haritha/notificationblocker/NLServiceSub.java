package com.bobdylan.haritha.notificationblocker;


import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.util.Date;

public class NLServiceSub extends NotificationListenerService {

    public static Date mDate;
    public AppLab mAppLab;


    @Override
    public IBinder onBind(Intent intent) {
        Log.i("bind", ": inside");

        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){

        Log.i("Notif posted", ": inside");

        mAppLab=AppLab.get(AppListFragment.context);
        AppType app=mAppLab.getApp(sbn.getPackageName());

        if(app.isChecked()) {

               

                mDate = new Date();
                app.addDate(String.format("%tR", mDate));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    app.setCount(app.getCount()+1) ;
                    cancelNotification(sbn.getKey());
                }
             
            }
        }



    @Override
    public void onNotificationRemoved(StatusBarNotification sbn){


    }

}
