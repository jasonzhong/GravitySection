package com.shineson.jason.gravitysection.datatool;

public interface IBaseDataManager {
    public void setSize(int size);
    public int getSize();

    public void addData(Object data);
    public Object getData(int pos);

    public void delData(int pos);
    public void clearData();

    public void modifyData(int pos, Object data);
}
