package com.bobdylan.haritha.notificationblocker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class AppActivity extends AppCompatActivity {

    private String appPkg;
    private AppType mApp;
    private ImageView mImageView;

    public static Intent newIntent(Context context, String pkg){
        Intent intent= new Intent(context, AppActivity.class);
        intent.putExtra("app_pkg", pkg);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        appPkg =getIntent().getStringExtra("app_pkg");
        mApp=AppLab.get(this).getApp(appPkg);
        mImageView= (ImageView)this.findViewById(R.id.app_imgview);
        mImageView.setImageDrawable(mApp.getIcon());
        TextView title=(TextView)findViewById(R.id.app_title);
        title.setText(mApp.getLabel()+ " ("+mApp.getCount()+")");
        TextView dates=(TextView)findViewById(R.id.app_timestamp);
        StringBuffer times=new StringBuffer();

        for(String d: mApp.getDates()){
            times.append(d+"\n");
        }
        dates.setText(times);

    }
}
