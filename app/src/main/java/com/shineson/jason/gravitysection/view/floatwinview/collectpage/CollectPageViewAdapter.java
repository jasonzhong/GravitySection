package com.shineson.jason.gravitysection.view.floatwinview.collectpage;

import android.content.Context;
import android.widget.TextView;

import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.common.listviewutil.BaseCardsAdapter;
import com.shineson.jason.gravitysection.common.listviewutil.BaseViewHolder;

public class CollectPageViewAdapter extends BaseCardsAdapter {

    public CollectPageViewAdapter(Context context) {
        super(context, R.layout.collectpage_listview);
    }

    @Override
    public void setViewHolder(int position, BaseViewHolder baseViewHolder) {
        TextView textView = baseViewHolder.getView(R.id.tv_url);
        textView.setText((String) (position + ""));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
