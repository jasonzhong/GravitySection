package com.shineson.jason.gravitysection.view.floatwinview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.common.sharedpref.CollectPreferencesManager;

public class FloatWinView extends LinearLayout {

    private int mTouchStartX = 0;
    private int mTouchStartY = 0;

    private int mEventStartX = 0;
    private int mEventStartY = 0;

    private int mLastEndX = 0;
    private int mLastEndY = 0;

    private int mViewWidth = 0;
    private int mViewHeight = 0;

    private WindowManager mWindowManager = null;
    private WindowManager.LayoutParams mWindowManagerParams = null;

    private TextView mCollectNumberView = null;

    private OnClickListener mClickListener = null;

    public FloatWinView(Context context) {
        super(context);
        initFloatWin(context);
    }

    public void initFloatWin(Context context) {
        mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.activity_floatwin, this);

        mCollectNumberView = (TextView) findViewById(R.id.floatwin_collect_number);

        int size = CollectPreferencesManager.getInstance().getCollectionWebSize();
        mCollectNumberView.setText("" + size);
        mCollectNumberView.setTextColor(Color.WHITE);

        View view = findViewById(R.id.floatwin_layout);
        mViewWidth = view.getLayoutParams().width;
        mViewHeight = view.getLayoutParams().height;
    }

    public int getViewWidthEx() {
        return mViewWidth;
    }

    public int getViewHeightEx() {
        return mViewHeight;
    }

    public void setParams(WindowManager.LayoutParams params) {
        mWindowManagerParams = params;
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
                    updateView();
                    mTouchStartX = mTouchStartY = 0;
                } else {
                    //mClickListener.onClick(this);
                    mLastEndX = mTouchStartX;
                    mLastEndY = mTouchStartY;

                    Point point = new Point();
                    mWindowManager.getDefaultDisplay().getSize(point);

                    updateView(point.x - 50 - mTouchStartX, (point.y / 4) - 50 - 25 - 10 - mTouchStartY);
                }
                break;
            default:
                break;
        }

        return true;
    }

    public void updateView(int x, int y) {
        mWindowManagerParams.x = x;
        mWindowManagerParams.y = y;
        mWindowManager.updateViewLayout(this, mWindowManagerParams);
    }

    public void updateView() {
        int size = CollectPreferencesManager.getInstance().getCollectionWebSize();
        mCollectNumberView.setText("" + size);

        updateView(mTouchStartX - mLastEndX, mTouchStartY - mLastEndY);
    }
}
