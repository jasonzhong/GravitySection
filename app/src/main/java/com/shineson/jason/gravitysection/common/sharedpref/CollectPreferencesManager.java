package com.shineson.jason.gravitysection.common.sharedpref;

import android.content.Context;

import com.shineson.jason.gravitysection.DaemonApplication;

public class CollectPreferencesManager extends PreferencesManager {
    private static final String NEWSFEED_PREFERENCE_NAME = "collect_preference_name";

    private static final String KEY_COLLECTION_WEB = "key_collection_web";
    private static final String KEY_COLLECTION_WEB_SIZE = "key_collection_web_size";

    private static CollectPreferencesManager mInstance = null;
    private CollectPreferencesManager() {
        super(DaemonApplication.getContext(), NEWSFEED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static CollectPreferencesManager getInstance() {
        if(mInstance == null){
            mInstance = new CollectPreferencesManager();
        }
        return mInstance;
    }

    public void setCollectionWebSize(int size) {
        putInt(KEY_COLLECTION_WEB_SIZE, size);
        commit();
    }

    public int getCollectionWebSize() {
        return getInt(KEY_COLLECTION_WEB_SIZE, 0);
    }

    public void setCollectionWeb(String url) {
        int size = getCollectionWebSize();
        String indexWeb = String.format("%s%d", KEY_COLLECTION_WEB, size);
        putString(indexWeb, url);

        setCollectionWebSize(size + 1);
        commit();
    }

    public String getCollectionWeb(int index, String def) {
        String indexWeb = String.format("%s%d", KEY_COLLECTION_WEB, index);
        String data = getString(indexWeb, def);
        return data;
    }
}
