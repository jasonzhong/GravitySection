package com.shineson.jason.gravitysection.view.maincollectionview.webbrowserview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shineson.jason.gravitysection.DaemonApplication;
import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.view.floatwinview.FloatWinManager;
import com.shineson.jason.gravitysection.view.maincollectionview.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebBrowserFragment extends BaseFragment {

    @Nullable @Bind(R.id.lv_card) ListView mLvCard;

    private WebBrowserViewList mWebBrowserViewList = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webbrowser_item, container, false);
        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void initView() {
        if (mLvCard == null) {
            return;
        }
        mWebBrowserViewList = new WebBrowserViewList(mLvCard, DaemonApplication.getContext());
        ArrayList<HashMap<String, Object>> itemsLists = getFileListOnDownloadDir("/storage/");
        mWebBrowserViewList.setItemList(itemsLists);

        if (!FloatWinManager.getInstance().isFloatWinShowing()) {
            FloatWinManager.getInstance()
                    .createFloatWinView(DaemonApplication.getContext())
                    .showFloatWinWebPageView("https://about.me/zhonghuajian");
        }
    }

    public ArrayList<HashMap<String, Object>> getFileListOnDownloadDir(String path) {
        ArrayList<HashMap<String, Object>> UrlList;
        UrlList = GetUrlName(path);
        return UrlList;
    }

    public static ArrayList<HashMap<String, Object>> GetUrlName(String fileAbsolutePath) {
        ArrayList<HashMap<String, Object>> urlList = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("urlname", "关于我");
        hashMap.put("url", "https://about.me/zhonghuajian");
        urlList.add(hashMap);
        return urlList;
    }
}
