package com.shineson.jason.gravitysection;

import android.app.Application;
import android.content.Context;

public class DaemonApplication extends Application {

    private static final String TAG = DaemonApplication.class.getSimpleName();
    private static Context mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
