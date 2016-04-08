package com.shineson.jason.gravitysection.view.floatwinview;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.shineson.jason.gravitysection.DaemonApplication;
import com.shineson.jason.gravitysection.common.sharedpref.CollectPreferencesManager;
import com.shineson.jason.gravitysection.view.floatwinview.collectpage.FloatWinCollectPageView;
import com.shineson.jason.gravitysection.view.floatwinview.webpage.FloatWinWebPageView;

public class FloatWinManager {

    private static FloatWinMainView mFloatWinMainView = null;
    private static FloatWinWebPageView mFloatWinWebPageView = null;
    private static FloatWinCollectPageView mFloatWinCollectPageView = null;

    private static WindowManager mWindowManager = null;

    private static Context mContext = null;

    private volatile static FloatWinManager instance = null;
    private FloatWinManager() {}

    public static FloatWinManager getInstance() {
        if(instance == null) {
            synchronized (FloatWinManager.class) {
                if(instance == null)
                    instance = new FloatWinManager();
            }
        }
        return instance;
    }

    private WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    private Display getWindowDefaultDisplay(Context context) {
        return getWindowManager(context).getDefaultDisplay();
    }

    public FloatWinManager createFloatWinView(final Context context) {
        if (mFloatWinMainView != null) {
            return this;
        }

        mContext = context;

        WindowManager windowManager = getWindowManager(mContext);
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);

        mFloatWinMainView = new FloatWinMainView(mContext);
        mFloatWinMainView.initParams();
        mFloatWinMainView.setParamsPoint(point);

        mFloatWinMainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CollectPreferencesManager.getInstance().getCollectionWebSize() > 1) {
                    if (!isFloatWinCollectpageShowing()) {
                        showFloatWinCollectPageView(mContext);
                    } else {
                        hideFloatWinCollectPageView();
                    }
                } else {
                    if (!isFloatWinWebpageShowing()) {
                        showFloatWinWebPageView(mContext);
                    } else {
                        hideFloatWinWebPageView();
                    }
                }
            }
        });

        if (mFloatWinMainView.getParams() != null) {
            windowManager.addView(mFloatWinMainView, mFloatWinMainView.getParams());
        }

        return this;
    }

    public void removeFloatWinView(Context context) {
        if (mFloatWinMainView != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(mFloatWinMainView);
            mFloatWinMainView = null;
        }
    }

    private void addFloatWinView(IFloatWinManager iFloatWinManager, Context context) {
        WindowManager windowManager = getWindowManager(context);
        if (iFloatWinManager.getParams() != null) {
            windowManager.addView(iFloatWinManager.getView(), iFloatWinManager.getParams());
        }
    }

    public void removeFloatWinView(IFloatWinManager iFloatWinManager, Context context) {
        if (iFloatWinManager != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(iFloatWinManager.getView());
        }
    }

    private void showFloatWinCollectPageView(Context context) {
        initCollectPageView(context);
    }

    private void initCollectPageView(Context context) {
        if (mFloatWinCollectPageView != null) {
            return;
        }

        mFloatWinCollectPageView = new FloatWinCollectPageView(context);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFloatWinCollectPageView();

                if (mFloatWinMainView != null) {
                    mFloatWinMainView.updateView();
                }
            }
        };

        initPageView(context, mFloatWinCollectPageView, clickListener);
    }

    private void initPageView(Context context, BaseFloatWinPageView pageView, View.OnClickListener clickListener) {
        Point point = new Point();
        getWindowDefaultDisplay(context).getSize(point);

        pageView.setParams()
            .setViewGravity(Gravity.BOTTOM)
            .setViewHeight(point.y / 4 * 3 + 100)
            .setViewWidth(point.x);

        pageView.setOnClickListener(clickListener);

        addFloatWinView(pageView, context);
    }

    private void hideFloatWinCollectPageView() {
        removeFloatWinView(mFloatWinCollectPageView, DaemonApplication.getContext());
        mFloatWinCollectPageView = null;
    }

    private void showFloatWinWebPageView(Context context) {
        initWebPageView(context);
        mFloatWinWebPageView.loadWebUrl();
    }

    private void initWebPageView(Context context) {
        if (mFloatWinWebPageView != null) {
            return;
        }

        mFloatWinWebPageView = new FloatWinWebPageView(context);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFloatWinWebPageView();

                if (mFloatWinMainView != null) {
                    mFloatWinMainView.updateView();
                }
            }
        };
        initPageView(context, mFloatWinWebPageView, clickListener);
    }

    public void showFloatWinWebPageView(String url) {
        initWebPageView(mContext);
        mFloatWinWebPageView.loadWebUrl(url);
    }

    private void hideFloatWinWebPageView() {
        removeFloatWinView(mFloatWinWebPageView, DaemonApplication.getContext());
        mFloatWinWebPageView = null;
    }

    private boolean isFloatWinCollectpageShowing() {
        return mFloatWinCollectPageView != null;
    }
    private boolean isFloatWinWebpageShowing() {
        return mFloatWinWebPageView != null;
    }

    public boolean isFloatWinShowing() {
        return mFloatWinMainView != null;
    }
}
