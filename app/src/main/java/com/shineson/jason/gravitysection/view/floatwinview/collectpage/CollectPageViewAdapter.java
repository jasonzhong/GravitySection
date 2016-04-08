package com.shineson.jason.gravitysection.view.floatwinview.collectpage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.common.listviewutil.BaseCardsAdapter;
import com.shineson.jason.gravitysection.common.listviewutil.BaseViewHolder;

import java.util.HashMap;

public class CollectPageViewAdapter extends BaseCardsAdapter {

    private Drawable mSelectedDrawable = null;
    private Drawable mUnSelectedDrawable = null;

    public CollectPageViewAdapter(Context context) {
        super(context, R.layout.collectpage_listview);

        mSelectedDrawable = context.getResources().getDrawable(R.drawable.webbrowser_app_select_checked);
        mUnSelectedDrawable = context.getResources().getDrawable(R.drawable.webbrowser_app_select_unchecked);
    }

    @Override
    public void setViewHolder(int position, BaseViewHolder baseViewHolder) {
        TextView textView = baseViewHolder.getView(R.id.tv_url);
        HashMap<String, Object> urlList = getItemListByPos(position);
        textView.setText(urlList.get("urlname").toString());

        ImageView imageView = baseViewHolder.getView(R.id.iv_check);
        HashMap<String, Object> urlCheck = getItemListByPos(position);
        boolean check = (boolean)urlCheck.get("check");
        if (check) {
            imageView.setImageDrawable(mSelectedDrawable);
        } else {
            imageView.setImageDrawable(mUnSelectedDrawable);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
