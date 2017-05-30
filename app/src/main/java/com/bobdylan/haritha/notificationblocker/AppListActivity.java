package com.bobdylan.haritha.notificationblocker;

import android.support.v4.app.Fragment;


public class AppListActivity extends SingleFragmentActivity {

    protected Fragment newFragment() {
        return new AppListFragment();
    }
}
