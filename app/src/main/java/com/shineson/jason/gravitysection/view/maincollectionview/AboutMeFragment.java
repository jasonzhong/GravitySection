package com.shineson.jason.gravitysection.view.maincollectionview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shineson.jason.gravitysection.R;

public class AboutMeFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aboutme_item, container, false);
    }
}
