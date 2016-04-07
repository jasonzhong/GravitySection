package com.shineson.jason.gravitysection.view.maincollectionview.webbrowserview;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.common.listviewutil.BaseCardsAdapter;
import com.shineson.jason.gravitysection.common.listviewutil.BaseCardsView;

public class WebBrowserViewList extends BaseCardsView {

    private WebBrowserViewAdapter mWebBrowserViewAdapter = null;
    private ListItemBtnClickListener mListItemBtnClickListener = null;
    private ListOnItemBtnClickListener mListOnItemBtnClickListener = null;

    public WebBrowserViewList(ListView listView, Context attachContext) {
        super(listView, attachContext);
        initView(attachContext);
    }

    private void initView(Context attachContext) {
        mWebBrowserViewAdapter = new WebBrowserViewAdapter(attachContext, R.layout.fragment_webbrowser_listview);
        mListItemBtnClickListener = new ListItemBtnClickListener();
        mListOnItemBtnClickListener = new ListOnItemBtnClickListener();

        showBaseCards();
    }

    @Override
    protected BaseCardsAdapter getCardsAdapter() {
        return mWebBrowserViewAdapter;
    }

    @Override
    protected View.OnClickListener getCardsListener() {
        return mListOnItemBtnClickListener;
    }

    @Override
    protected AdapterView.OnItemClickListener getCardsOneItemListener() {
        return mListItemBtnClickListener;
    }

    private final class ListOnItemBtnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int nId = v.getId();
        }
    }

    private class ListItemBtnClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        }
    }
}
