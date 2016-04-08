package com.shineson.jason.gravitysection.datatool;

public class DataManager {
    public enum DATATYPE {
        enum_none,
        enum_webbrowser
    }

    private WebBrowserDataManager mWebBrowserDataManager = null;

    private volatile static DataManager instance = null;

    private DataManager() {
        mWebBrowserDataManager = new WebBrowserDataManager();
    }

    public static DataManager getInstance() {
        if(instance == null) {
            synchronized (DataManager.class) {
                if(instance == null)
                    instance = new DataManager();
            }
        }
        return instance;
    }

    public void addWebData(String url) {
        addData(DATATYPE.enum_webbrowser, url);
    }

    private void addData(DATATYPE datatype, Object data) {
        IBaseDataManager iBaseDataManager = null;
        if (datatype == DATATYPE.enum_webbrowser) {
            iBaseDataManager = mWebBrowserDataManager;
        }
        addData(iBaseDataManager, data);
    }

    private void addData(IBaseDataManager iBaseDataManager, Object data) {
        iBaseDataManager.addData(data);
    }

    public String getWebData(int pos) {
        return getData(DATATYPE.enum_webbrowser, pos);
    }

    private String getData(DATATYPE datatype, int pos) {
        IBaseDataManager iBaseDataManager = null;
        if (datatype == DATATYPE.enum_webbrowser) {
            iBaseDataManager = mWebBrowserDataManager;
        }
        return (String)getData(iBaseDataManager, pos);
    }

    private Object getData(IBaseDataManager iBaseDataManager, int pos) {
        return iBaseDataManager.getData(pos);
    }

    public int getWebSize() {
        return getSize(DATATYPE.enum_webbrowser);
    }

    private int getSize(DATATYPE datatype) {
        IBaseDataManager iBaseDataManager = null;
        if (datatype == DATATYPE.enum_webbrowser) {
            iBaseDataManager = mWebBrowserDataManager;
        }
        return getSize(iBaseDataManager);
    }

    private int getSize(IBaseDataManager iBaseDataManager) {
        return iBaseDataManager.getSize();
    }
}
