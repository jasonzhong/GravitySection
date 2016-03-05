package com.shineson.jason.gravitysection.view.floatwinview.collectpage;

import android.content.Context;
import android.graphics.Point;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.shineson.jason.gravitysection.DaemonApplication;
import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.view.floatwinview.BaseFloatWinPageView;

public class FloatWinCollectPageView extends BaseFloatWinPageView {

    private final String LOG_TAG = FloatWinCollectPageView.class.getSimpleName();

    private ImageButton mCloseBtn = null;

    public FloatWinCollectPageView(Context context) {
        super(context, R.layout.floatwin_collectpage);
        initFloatWinCollectPage();
    }

    @Override
    public void setParams() {
        setGravity(Gravity.BOTTOM);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        setHeight(point.y / 4 * 3 + 100);
        setWidth(point.x);
    }

    @Override
    public View getView() {
        return this;
    }

    private void initFloatWinCollectPage() {
        View view = findViewById(R.id.floatwin_collectpage_layout);

        mCloseBtn = (ImageButton) view.findViewById(R.id.floatwin_collectpage_btn_close);
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onClick(FloatWinCollectPageView.this);
            }
        });
    }
}
