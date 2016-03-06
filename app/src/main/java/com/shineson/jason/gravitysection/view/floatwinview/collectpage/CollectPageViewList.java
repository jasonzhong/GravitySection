package com.shineson.jason.gravitysection.view.floatwinview.collectpage;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shineson.jason.gravitysection.DaemonApplication;
import com.shineson.jason.gravitysection.common.listviewutil.BaseCardsAdapter;
import com.shineson.jason.gravitysection.common.listviewutil.BaseCardsView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class CollectPageViewList extends BaseCardsView {

    private CollectPageViewAdapter mCollectPageViewAdapter = null;
    private ListItemBtnClickListener mListItemBtnClickListener = null;
    private ListOnItemBtnClickListener mListOnItemBtnClickListener = null;

    public CollectPageViewList(ListView listView, Context attachContext) {
        super(listView, attachContext);
        onInitView();
    }

    private void onInitView() {
        mCollectPageViewAdapter = new CollectPageViewAdapter(DaemonApplication.getContext());
        mListItemBtnClickListener = new ListItemBtnClickListener();
        mListOnItemBtnClickListener = new ListOnItemBtnClickListener();

        showBaseCards();
    }

    @Override
    protected BaseCardsAdapter getCardsAdapter() {
        return mCollectPageViewAdapter;
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
