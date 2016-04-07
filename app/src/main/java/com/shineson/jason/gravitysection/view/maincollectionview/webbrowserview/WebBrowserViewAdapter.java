package com.shineson.jason.gravitysection.view.maincollectionview.webbrowserview;

import android.content.Context;
import android.widget.TextView;

import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.common.listviewutil.BaseCardsAdapter;
import com.shineson.jason.gravitysection.common.listviewutil.BaseViewHolder;

import java.util.HashMap;

public class WebBrowserViewAdapter extends BaseCardsAdapter {

    public WebBrowserViewAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void setViewHolder(int position, BaseViewHolder baseViewHolder) {
        TextView textView = baseViewHolder.getView(R.id.tv_url);
        HashMap<String, Object> urlList = getItemListByPos(position);
        textView.setText(urlList.get("urlname").toString());
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
