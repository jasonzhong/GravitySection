package com.shineson.jason.gravitysection.view.floatwinview.collectpage;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.datatool.DataManager;
import com.shineson.jason.gravitysection.view.floatwinview.BaseFloatWinPageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FloatWinCollectPageView extends BaseFloatWinPageView {

    private final String LOG_TAG = FloatWinCollectPageView.class.getSimpleName();

    @Nullable @Bind(R.id.lv_card) ListView mLvCard;
    @Nullable @Bind(R.id.btn_close) ImageButton mCloseBtn;

    private CollectPageViewList mCollectPageViewView = null;

    public FloatWinCollectPageView(Context context) {
        super(context, R.layout.floatwin_collectpage);
        initFloatWinCollectPage();

        mCollectPageViewView = new CollectPageViewList(mLvCard, context);

        ArrayList<HashMap<String, Object>> itemsLists = getUrlListOnDownloadDir();
        mCollectPageViewView.setItemList(itemsLists);
    }

    public ArrayList<HashMap<String, Object>> getUrlListOnDownloadDir() {
        ArrayList<HashMap<String, Object>> UrlList;
        UrlList = GetUrlName();
        return UrlList;
    }

    public static ArrayList<HashMap<String, Object>> GetUrlName() {
        ArrayList<HashMap<String, Object>> urlList = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("urlname", "关于我");
        hashMap.put("url", "https://about.me/zhonghuajian");
        hashMap.put("check", false);
        urlList.add(hashMap);

        int size = DataManager.getInstance().getWebSize();
        for (int i = 0; i < size; ++i) {
            HashMap<String, Object> hashMapWeb = new HashMap<String, Object>();
            hashMapWeb.put("urlname", DataManager.getInstance().getWebData(i));
            hashMapWeb.put("url", DataManager.getInstance().getWebData(i));
            hashMapWeb.put("check", false);
            urlList.add(hashMapWeb);
        }
        return urlList;
    }

    @Override
    public BaseFloatWinPageView setParams() {
        return this;
    }

    @Override
    public View getView() {
        return this;
    }

    private void initFloatWinCollectPage() {
        View view = findViewById(R.id.floatwin_collectpage_layout);

        ButterKnife.bind(this, view);

        if (null == mCloseBtn) {
            return;
        }

        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onClick(FloatWinCollectPageView.this);
            }
        });
    }
}
