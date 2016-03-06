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

    private static Display getWindowDefaultDisplay(Context context) {
        return getWindowManager(context).getDefaultDisplay();
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
                if (CollectPreferencesManager.getInstance().getCollectionWebSize() > 1) {
                    if (!isFloatWinCollectpageShowing()) {
                        showFloatWinCollectPageView(DaemonApplication.getContext());
                    } else {
                        hideFloatWinCollectPageView();
                    }
                } else {
                    if (!isFloatWinWebpageShowing()) {
                        showFloatWinWebPage(DaemonApplication.getContext());
                    } else {
                        hideFloatWinWebPageView();
                    }
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

    private static void addFloatWinView(IFloatWinManager iFloatWinManager, Context context) {
        WindowManager windowManager = getWindowManager(context);
        if (iFloatWinManager.getParams() != null) {
            windowManager.addView(iFloatWinManager.getView(), iFloatWinManager.getParams());
        }
    }

    public static void removeFloatWinView(IFloatWinManager iFloatWinManager, Context context) {
        if (iFloatWinManager != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(iFloatWinManager.getView());
        }
    }

    private static void showFloatWinCollectPageView(Context context) {
        if (mFloatWinCollectPageView != null) {
            return;
        }

        Point point = new Point();
        getWindowDefaultDisplay(context).getSize(point);

        mFloatWinCollectPageView = new FloatWinCollectPageView(context);
        mFloatWinCollectPageView.setParams()
            .setViewGravity(Gravity.BOTTOM)
            .setViewHeight(point.y / 4 * 3 + 100)
            .setViewWidth(point.x);

        mFloatWinCollectPageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFloatWinCollectPageView();

                if (mFloatWinView != null) {
                    mFloatWinView.updateView();
                }
            }
        });

        addFloatWinView(mFloatWinCollectPageView, context);
    }

    private static void hideFloatWinCollectPageView() {
        removeFloatWinView(mFloatWinCollectPageView, DaemonApplication.getContext());
        mFloatWinCollectPageView = null;
    }

    private static void showFloatWinWebPage(Context context) {
        if (mFloatWinWebPageView != null) {
            return;
        }

        Point point = new Point();
        getWindowDefaultDisplay(context).getSize(point);

        mFloatWinWebPageView = new FloatWinWebPageView(context);
        mFloatWinWebPageView.setParams()
            .setViewHeight(point.y / 4 * 3 + 100)
            .setViewWidth(point.x)
            .setViewGravity(Gravity.BOTTOM);

        mFloatWinWebPageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFloatWinWebPageView();

                if (mFloatWinView != null) {
                    mFloatWinView.updateView();
                }
            }
        });

        addFloatWinView(mFloatWinWebPageView, context);
    }

    private static void hideFloatWinWebPageView() {
        removeFloatWinView(mFloatWinWebPageView, DaemonApplication.getContext());
        mFloatWinWebPageView = null;
    }

    private static boolean isFloatWinCollectpageShowing() {
        return mFloatWinCollectPageView != null;
    }
    private static boolean isFloatWinWebpageShowing() {
        return mFloatWinWebPageView != null;
    }

    public static boolean isFloatWinShowing() {
        return mFloatWinView != null;
    }
}
