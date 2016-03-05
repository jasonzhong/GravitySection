package com.shineson.jason.gravitysection.view.floatwinview.webpage;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shineson.jason.gravitysection.DaemonApplication;
import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.common.sharedpref.CollectPreferencesManager;

public class FloatWinWebPageView extends LinearLayout {
    private final String LOG_TAG = FloatWinWebPageView.class.getSimpleName();

    private WindowManager mWindowManager = null;
    private WindowManager.LayoutParams mParams = null;

    private Boolean isShown = false;

    private View mWindowView = null;
    private WebView mWebView = null;
    private TextView mTextView = null;
    private ImageButton mCloseBtn = null;
    private ImageButton mCollectBtn = null;

    private OnClickListener mClickListener = null;

    private String mWebUrl = "http://www.baidu.com";

    public FloatWinWebPageView(Context context) {
        super(context);
        initFloatWinWebpage(context);
    }

    @Override
    public void setOnClickListener(OnClickListener listener) {
        this.mClickListener = listener;
    }

    public WindowManager.LayoutParams getParams() {
        return mParams;
    }

    public void initFloatWinWebpage(Context context) {
        mWindowManager = (WindowManager) DaemonApplication.mContext
                .getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.floatwin_webpage, this);

        View view = findViewById(R.id.floatwin_webpage_layout);
        setTouchAction(view);
    }

    public void initParams() {
        if (isShown) {
            return;
        }
        isShown = true;

        mParams = new WindowManager.LayoutParams();
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        mParams.flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mParams.format = PixelFormat.TRANSLUCENT;

        mParams.height = getViewHeightEx();
        mParams.width = getViewWidthEx();

        mParams.gravity = Gravity.BOTTOM;
    }

    public void setParamsPoint(Point point) {
        if (mParams == null) {
            return;
        }
        mParams.x = point.x;
        mParams.y = point.y;
    }

    private int getViewWidthEx() {
        Point point = new Point();
        mWindowManager.getDefaultDisplay().getSize(point);

        return point.x;
    }

    private int getViewHeightEx() {
        Point point = new Point();
        mWindowManager.getDefaultDisplay().getSize(point);

        return (point.y / 4 * 3 + 100);
    }

    public void hideFloatWinWebpage() {
        if (isShown) {
            isShown = false;
        }
    }

    private void setTouchAction(View view) {
        mCloseBtn = (ImageButton) view.findViewById(R.id.floatwin_webpage_btn_close);
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onClick(FloatWinWebPageView.this);
            }
        });

        mCollectBtn = (ImageButton) view.findViewById(R.id.floatwin_webpage_btn_collect);
        mCollectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectPreferencesManager.getInstance().setCollectionWeb(mWebUrl);
                Toast.makeText(DaemonApplication.mContext, " ’≤ÿ" + mWebUrl, Toast.LENGTH_SHORT).show();
            }
        });

        mWebView = (WebView) view.findViewById(R.id.floatwin_webpage_webView);
        mWebView.loadUrl(mWebUrl);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.requestFocus(View.FOCUS_DOWN);

        mTextView = (TextView) view.findViewById(R.id.floatwin_webpage_text_url);
        mTextView.setText(mWebUrl);

        mWindowView = view.findViewById(R.id.floatwin_webpage_layout);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Rect rect = new Rect();
                mWindowView.getGlobalVisibleRect(rect);

                int X = (int) event.getX();
                int Y = (int) event.getY();

                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (!isEffectiveArea(X, Y, rect)) {
                            hideFloatWinWebpage();
                        }
                        break;
                }
                return true;
            }
        });
    }

    private boolean isEffectiveArea(int x, int y, Rect rect) {
        return rect.contains(x, y);
    }

}
