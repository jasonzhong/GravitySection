package com.shineson.jason.gravitysection.service.floatwinservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

import com.shineson.jason.gravitysection.DaemonApplication;
import com.shineson.jason.gravitysection.view.floatwinview.FloatWinManager;

import java.util.Timer;
import java.util.TimerTask;

public class FloatWinService extends Service {

    private Handler handler = new Handler();

    private Timer mTimer = null;
    private String mUrl = null;

    public FloatWinService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mUrl = (String)bundle.get("url");
        }

        if (mTimer == null) {
            mTimer = new Timer();
            mTimer.scheduleAtFixedRate(new MonitorTask(), 0, 1000);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                String url = mUrl;
                int start = url.indexOf("http");
                if (start != 0) {
                    url = url.substring(start, url.length());
                }

                FloatWinManager.getInstance()
                    .createFloatWinView(DaemonApplication.getContext())
                    .showFloatWinWebPageView(url);

            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
        mTimer = null;
    }

    /*private boolean isHome() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> rti = mActivityManager.getRunningTasks(0);
        return getHomes().contains(rti.get(0).topActivity.getPackageName());
    }

    private List<String> getHomes() {
        List<String> names = new ArrayList<String>();
        PackageManager packageManager = this.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo ri : resolveInfo) {
            names.add(ri.activityInfo.packageName);
        }
        return names;
    }*/

    class MonitorTask extends TimerTask {

        @Override
        public void run() {
            if (!FloatWinManager.getInstance().isFloatWinShowing()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        FloatWinManager.getInstance()
                                .createFloatWinView(DaemonApplication.getContext());
                    }
                });
            } /*else if (!isHome() && FloatWinManager.isFloatWinShowing()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        FloatWinManager.removeFloatWinView(DaemonApplication.mContext);
                    }
                });
            } else if (isHome() && FloatWinManager.isFloatWinShowing()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //MyWindowManager.updateUsedPercent(getApplicationContext());
                    }
                });
            }*/
        }

    }
}
