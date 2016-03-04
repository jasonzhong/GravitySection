package com.shineson.jason.gravitysection.view.floatwinview;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Gravity;
import android.view.WindowManager;

public class FloatWinManager {

    private static FloatWinView floatWinView = null;
    private static WindowManager.LayoutParams floatwinParams = null;

    private static WindowManager mWindowManager = null;

    private static WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    public static void createFloatWinView(Context context) {
        WindowManager windowManager = getWindowManager(context);
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        if (floatWinView != null) {
            return;
        }

        floatWinView = new FloatWinView(context);
        if (floatwinParams == null) {
            floatwinParams = new WindowManager.LayoutParams();
            floatwinParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            floatwinParams.format = PixelFormat.RGBA_8888;
            floatwinParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            floatwinParams.gravity = Gravity.LEFT | Gravity.TOP;
            floatwinParams.width = floatWinView.getViewWidthEx();
            floatwinParams.height = floatWinView.getViewHeightEx();
            floatwinParams.x = point.x;
            floatwinParams.y = point.y / 2;
        }
        floatWinView.setParams(floatwinParams);
        windowManager.addView(floatWinView, floatwinParams);
    }

    public static void removeFloatWinView(Context context) {
        if (floatWinView != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(floatWinView);
            floatWinView = null;
        }
    }

    public static boolean isFloatWinShowing() {
        return floatWinView != null;
    }
}
