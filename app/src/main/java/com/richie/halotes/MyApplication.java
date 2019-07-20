package com.richie.halotes;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


/**
 * Created by richie on 2/24/16.
 */
public class MyApplication extends Application {
    public static final String TAG = MyApplication.class
            .getSimpleName();

    private static MyApplication mInstance;



    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;


    }



    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

 @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

}
