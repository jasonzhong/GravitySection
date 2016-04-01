package com.shineson.jason.gravitysection.view.floatwinview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.common.sharedpref.CollectPreferencesManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FloatWinView extends LinearLayout {

    private int mTouchStartX = 0;
    private int mTouchStartY = 0;

    private int mEventStartX = 0;
    private int mEventStartY = 0;

    private int mViewWidth = 0;
    private int mViewHeight = 0;

    private WindowManager mWindowManager = null;
    private WindowManager.LayoutParams mWindowManagerParams = null;

    @Nullable @Bind(R.id.floatwin_collect_number) TextView mCollectNumberView;

    private OnClickListener mClickListener = null;

    public FloatWinView(Context context) {
        super(context);
        initFloatWin(context);
    }

    public void initFloatWin(Context context) {
        mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.activity_floatwin, this);

        ButterKnife.bind(this);

        int size = CollectPreferencesManager.getInstance().getCollectionWebSize();
        mCollectNumberView.setText("" + size);
        mCollectNumberView.setTextColor(Color.WHITE);

        View view = findViewById(R.id.floatwin_layout);
        mViewWidth = view.getLayoutParams().width;
        mViewHeight = view.getLayoutParams().height;
    }

    private int getViewWidthEx() {
        return mViewWidth;
    }

    private int getViewHeightEx() {
        return mViewHeight;
    }

    public WindowManager.LayoutParams getParams() {
        return mWindowManagerParams;
    }

    public void initParams() {
        if (mWindowManagerParams != null) {
            return;
        }

        mWindowManagerParams = new WindowManager.LayoutParams();
        mWindowManagerParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mWindowManagerParams.format = PixelFormat.RGBA_8888;
        mWindowManagerParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mWindowManagerParams.gravity = Gravity.LEFT | Gravity.TOP;
        mWindowManagerParams.width = getViewWidthEx();
        mWindowManagerParams.height = getViewHeightEx();
    }

    public void setParamsPoint(Point point) {
        if (mWindowManagerParams == null) {
            return;
        }
        mWindowManagerParams.x = point.x;
        mWindowManagerParams.y = point.y;
    }

    @Override
    public void setOnClickListener(OnClickListener listener) {
        this.mClickListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY() - 25;

        Rect rect = new Rect();
        getGlobalVisibleRect(rect);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchStartX = (int) event.getX();
                mTouchStartY = (int) event.getY() + 25;

                System.out.println("x:" + mEventStartX + " y:" + mEventStartY);

                mEventStartX = rawX;
                mEventStartY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                updateView(rawX - mTouchStartX, rawY - mTouchStartY);
                break;
            case MotionEvent.ACTION_UP:
                int moveDistanceX = rawX - mEventStartX;
                int moveDistanceY = rawY - mEventStartY;
                if ((moveDistanceX > 30 || moveDistanceX < -30) ||
                        (moveDistanceY > 30 || moveDistanceY < -30)) {
                    mTouchStartX = mTouchStartY = 0;
                } else {
                    mClickListener.onClick(this);
                    moveToTop();
                }
                break;
            default:
                break;
        }

        return true;
    }

    private void moveToTop() {
        Point point = new Point();
        mWindowManager.getDefaultDisplay().getSize(point);

        updateView(point.x - 50 - mTouchStartX, (point.y / 4) - 50 - 25 - 100 - mTouchStartY);
    }

    public void updateView(int x, int y) {
        mWindowManagerParams.x = x;
        mWindowManagerParams.y = y;
        mWindowManager.updateViewLayout(this, mWindowManagerParams);
    }

    public void updateView() {
        int size = CollectPreferencesManager.getInstance().getCollectionWebSize();
        mCollectNumberView.setText("" + size);

        updateView(mEventStartX - mTouchStartX, mEventStartY - mTouchStartY);
    }
}
