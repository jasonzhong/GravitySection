package com.shineson.jason.gravitysection.view.floatwinview.webpage;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.shineson.jason.gravitysection.DaemonApplication;
import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.common.sharedpref.CollectPreferencesManager;
import com.shineson.jason.gravitysection.datatool.DataManager;
import com.shineson.jason.gravitysection.view.floatwinview.BaseFloatWinPageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FloatWinWebPageView extends BaseFloatWinPageView {

    private final String LOG_TAG = FloatWinWebPageView.class.getSimpleName();

    private View mWindowView = null;
    private WebView mWebView = null;
    private TextView mTextView = null;

    @Bind(R.id.floatwin_webpage_btn_close) ImageButton mCloseBtn;
    @Bind(R.id.floatwin_webpage_btn_collect) ImageButton mCollectBtn;

    private String mWebUrl = "http://www.baidu.com";

    public FloatWinWebPageView(Context context) {
        super(context, R.layout.floatwin_webpage);
        initFloatWinWebpage();
    }

    public void removeFloatWinWebPageView() {
        ButterKnife.unbind(this);
    }

    @Override
    public BaseFloatWinPageView setParams() {
        return this;
    }

    public void loadWebUrl() {
        reloadWebPage();
    }

    public void loadWebUrl(String url) {
        mWebUrl = url;
        reloadWebPage();
    }

    private void reloadWebPage() {
        if (null == mWebView) {
            return;
        }
        mWebView.loadUrl(mWebUrl);
        mTextView.setText(mWebUrl);
    }

    public void initFloatWinWebpage() {
        View view = findViewById(R.id.floatwin_webpage_layout);
        ButterKnife.bind(this, view);
        setTouchAction(view);
    }

    private void setTouchAction(View view) {
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onClick(FloatWinWebPageView.this);
            }
        });

        mCollectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.getInstance().addWebData(mWebUrl);
                Toast.makeText(DaemonApplication.getContext(), "已收藏" + mWebUrl, Toast.LENGTH_SHORT).show();
            }
        });

        mTextView = (TextView) view.findViewById(R.id.floatwin_webpage_text_url);
        mTextView.setText(mWebUrl);

        initWebView(view);

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
                            mClickListener.onClick(FloatWinWebPageView.this);
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void initWebView(View view) {
        mWebView = (WebView) view.findViewById(R.id.floatwin_webpage_webView);
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
    }

    private boolean isEffectiveArea(int x, int y, Rect rect) {
        return rect.contains(x, y);
    }

    @Override
    public View getView() {
        return this;
    }
}
