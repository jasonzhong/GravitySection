package com.shineson.jason.gravitysection.view.floatwinview;

import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.WindowManager;

import com.shineson.jason.gravitysection.DaemonApplication;
import com.shineson.jason.gravitysection.view.floatwinview.collectpage.FloatWinCollectPageView;
import com.shineson.jason.gravitysection.view.floatwinview.webpage.FloatWinWebPageView;

public class FloatWinManager {

    private static FloatWinView mFloatWinView = null;
    private static FloatWinWebPageView mFloatWinWebPageView = null;
    private static FloatWinCollectPageView mFloatWinCollectPageView = null;

    private static WindowManager mWindowManager = null;

    private static WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    public static void createFloatWinView(final Context context) {
        if (mFloatWinView != null) {
            return;
        }

        WindowManager windowManager = getWindowManager(context);
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);

        mFloatWinView = new FloatWinView(context);
        mFloatWinView.initParams();
        mFloatWinView.setParamsPoint(point);

        mFloatWinView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFloatWinWebpageShowing()) {
                    showFloatWinWebpage(DaemonApplication.mContext);
                } else {
                    hideFloatWinWebpage(DaemonApplication.mContext);
                }
            }
        });

        if (mFloatWinView.getParams() != null) {
            windowManager.addView(mFloatWinView, mFloatWinView.getParams());
        }
    }

    public static void removeFloatWinView(Context context) {
        if (mFloatWinView != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(mFloatWinView);
            mFloatWinView = null;
        }
    }

    private static void showFloatWinCollectPageView(Context context) {
        if (mFloatWinCollectPageView != null) {
            return;
        }

        IFloatWinManager iFloatWinManager = null;
        mFloatWinCollectPageView = new FloatWinCollectPageView(context);
        mFloatWinCollectPageView.setParams();

        mFloatWinCollectPageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFloatWinWebpage(DaemonApplication.mContext);
                if (mFloatWinView != null) {
                    mFloatWinView.updateView();
                }
            }
        });

        iFloatWinManager = mFloatWinCollectPageView;
        WindowManager windowManager = getWindowManager(context);
        if (iFloatWinManager.getParams() != null) {
            windowManager.addView(iFloatWinManager.getView(), iFloatWinManager.getParams());
        }
    }

    private static void hideFloatWinCollectPageView(Context context) {
        if (mFloatWinCollectPageView != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(mFloatWinCollectPageView);
            mFloatWinCollectPageView = null;
        }
    }

    private static void showFloatWinWebpage(Context context) {
        if (mFloatWinWebPageView != null) {
            return;
        }

        mFloatWinWebPageView = new FloatWinWebPageView(context);
        mFloatWinWebPageView.initParams();

        mFloatWinWebPageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFloatWinWebpage(DaemonApplication.mContext);
                if (mFloatWinView != null) {
                    mFloatWinView.updateView();
                }
            }
        });

        WindowManager windowManager = getWindowManager(context);
        if (mFloatWinWebPageView.getParams() != null) {
            windowManager.addView(mFloatWinWebPageView, mFloatWinWebPageView.getParams());
        }
    }

    private static void hideFloatWinWebpage(Context context) {
        if (mFloatWinWebPageView != null) {
            mFloatWinWebPageView.hideFloatWinWebpage();
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(mFloatWinWebPageView);
            mFloatWinWebPageView = null;
        }
    }

    private static boolean isFloatWinWebpageShowing() {
        return mFloatWinWebPageView != null;
    }

    public static boolean isFloatWinShowing() {
        return mFloatWinView != null;
    }
}
