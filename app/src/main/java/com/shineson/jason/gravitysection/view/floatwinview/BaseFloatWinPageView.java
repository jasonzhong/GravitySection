package com.shineson.jason.gravitysection.view.floatwinview;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.shineson.jason.gravitysection.DaemonApplication;

public abstract class BaseFloatWinPageView extends LinearLayout implements IFloatWinManager {

    private final String LOG_TAG = BaseFloatWinPageView.class.getSimpleName();

    private WindowManager mWindowManager = null;
    private WindowManager.LayoutParams mParams = null;

    public OnClickListener mClickListener = null;

    public Boolean isShown = false;

    public BaseFloatWinPageView(Context context, int layoutID) {
        this(context);
        initFloatWinPage(context, layoutID);
    }

    private BaseFloatWinPageView(Context context) {
        super(context);
    }

    @Override
    public void setOnClickListener(OnClickListener listener) {
        this.mClickListener = listener;
    }

    public WindowManager.LayoutParams getParams() {
        return mParams;
    }

    public BaseFloatWinPageView setViewGravity(int gravity) {
        if (mParams != null) {
            mParams.gravity = gravity;
        }
        return this;
    }

    public BaseFloatWinPageView setViewWidth(int width) {
        if (mParams != null) {
            mParams.width = width;
        }
        return this;
    }

    public BaseFloatWinPageView setViewHeight(int height) {
        if (mParams != null) {
            mParams.height = height;
        }
        return this;
    }

    public BaseFloatWinPageView setViewParamsX(int x) {
        if (mParams != null) {
            mParams.x = x;
        }
        return this;
    }

    public BaseFloatWinPageView setViewParamsY(int y) {
        if (mParams != null) {
            mParams.y = y;
        }
        return this;
    }

    abstract public BaseFloatWinPageView setParams();

    private void initFloatWinPageParams(int layoutID) {
        if (isShown) {
            return;
        }
        isShown = true;

        mParams = new WindowManager.LayoutParams();
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        mParams.flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mParams.format = PixelFormat.TRANSLUCENT;

        mParams.gravity = Gravity.BOTTOM;
    }

    private void initFloatWinPage(Context context, int layoutID) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) DaemonApplication.getContext()
                    .getSystemService(Context.WINDOW_SERVICE);
        }
        LayoutInflater.from(context).inflate(layoutID, this);

        initFloatWinPageParams(layoutID);
    }

    public WindowManager getWindowManager() {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) DaemonApplication.getContext()
                    .getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }
}
