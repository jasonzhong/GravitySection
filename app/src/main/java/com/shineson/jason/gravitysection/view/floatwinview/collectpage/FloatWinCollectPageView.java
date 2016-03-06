package com.shineson.jason.gravitysection.view.floatwinview.collectpage;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.view.floatwinview.BaseFloatWinPageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FloatWinCollectPageView extends BaseFloatWinPageView {

    private final String LOG_TAG = FloatWinCollectPageView.class.getSimpleName();

    private ListView mLvCard = null;
    private ImageButton mCloseBtn = null;

    private CollectPageViewList mCollectPageViewView = null;

    public FloatWinCollectPageView(Context context) {
        super(context, R.layout.floatwin_collectpage);
        initFloatWinCollectPage();

        mLvCard = (ListView) findViewById(R.id.lv_card);

        mCollectPageViewView = new CollectPageViewList(mLvCard, context);

        ArrayList<HashMap<String, Object>> itemsLists = getFileListOnDownloadDir("/storage/");
        mCollectPageViewView.setItemList(itemsLists);
    }

    public ArrayList<HashMap<String, Object>> getFileListOnDownloadDir(String path) {
        ArrayList<HashMap<String, Object>> fileList;
        fileList = GetFileName(path);
        return fileList;
    }

    public static ArrayList<HashMap<String, Object>> GetFileName(String fileAbsolutePath) {
        ArrayList<HashMap<String, Object>> fileList = new ArrayList<HashMap<String, Object>>();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();

        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("fullpath", subFile[iFileLength].getPath());
            hashMap.put("filename", subFile[iFileLength].getName());
            hashMap.put("imageview", null);
            fileList.add(hashMap);
        }
        return fileList;
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

        mCloseBtn = (ImageButton) view.findViewById(R.id.btn_close);
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onClick(FloatWinCollectPageView.this);
            }
        });
    }
}
