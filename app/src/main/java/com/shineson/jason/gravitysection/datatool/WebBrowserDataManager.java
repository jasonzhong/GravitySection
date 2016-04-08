package com.shineson.jason.gravitysection.datatool;

import com.shineson.jason.gravitysection.common.sharedpref.CollectPreferencesManager;

public class WebBrowserDataManager implements IBaseDataManager {

    public WebBrowserDataManager() {}

    @Override
    public void setSize(int size) {

    }

    @Override
    public int getSize() {
        return CollectPreferencesManager.getInstance().getCollectionWebSize();
    }

    @Override
    public void addData(Object data) {
        CollectPreferencesManager.getInstance().setCollectionWeb((String)data);
    }

    @Override
    public Object getData(int pos) {
        return CollectPreferencesManager.getInstance().getCollectionWeb(pos, "");
    }

    @Override
    public void delData(int pos) {

    }

    @Override
    public void clearData() {

    }

    @Override
    public void modifyData(int pos, Object data) {

    }
}
