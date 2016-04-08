package com.shineson.jason.gravitysection.view.maincollectionview;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.common.utils.FragmentCreator;
import com.shineson.jason.gravitysection.view.maincollectionview.webbrowserview.WebBrowserFragment;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainCollectionFragmentActivity extends BasicFragmentActivity implements View.OnClickListener {
    private enum FRAGMENT_TYPE {
        enum_webbrowser,
        enum_other,
        enum_aboutme
    }

    private List<FragmentCreator> mMenuList = null;

    @Nullable @Bind(R.id.btn_back) TextView mBtnBack;
    @Nullable @Bind(R.id.tv_webbrowser) TextView mBtnWebbrowser;
    @Nullable @Bind(R.id.tv_other) TextView mBtnOther;
    @Nullable @Bind(R.id.tv_aboutme) TextView mBtnAboutme;
    @Nullable @Bind(R.id.main_Pager) ViewPager mMainPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_collection);

        ButterKnife.bind(this);

        findViewById(R.id.collection_webbrowser).setOnClickListener(this);
        findViewById(R.id.collection_other).setOnClickListener(this);
        findViewById(R.id.collection_aboutme).setOnClickListener(this);

        initView();
    }

    private void initView() {
        mMenuList = new LinkedList<FragmentCreator>();
        mMenuList.add(new FragmentCreator(WebBrowserFragment.class, R.string.title_webbrowser));
        mMenuList.add(new FragmentCreator(AboutMeFragment.class, R.string.title_aboutme));
        mMenuList.add(new FragmentCreator(AboutMeFragment.class, R.string.title_aboutme));

        setMainPageAdapter();
        addMainPageChangeListener();

        if (mBtnBack != null) {
            mBtnBack.setOnClickListener(this);
        }

        setFragmentTab(FRAGMENT_TYPE.enum_webbrowser.ordinal());
    }

    private void addMainPageChangeListener() {
        if (null == mMainPager) {
            return;
        }

        mMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setFragmentTab(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    private void setMainPageAdapter() {
        if (null == mMainPager) {
            return;
        }

        mMainPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mMenuList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mMenuList.get(position).newInstance();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getResources().getString(mMenuList.get(position).getTitleResId());
            }
        });
    }

    private void setFragmentTab(int position) {
        if (mMainPager == null) {
            return;
        }
        mMainPager.setCurrentItem(position, true);


        if (mBtnAboutme == null || mBtnWebbrowser == null || mBtnOther == null) {
            return;
        }
        mBtnWebbrowser.setSelected(false);
        mBtnAboutme.setSelected(false);
        mBtnOther.setSelected(false);

        if (position == 0) {
            mBtnWebbrowser.setSelected(true);
        } else if (position == 1) {
            mBtnOther.setSelected(true);
        } else if (position == 2) {
            mBtnAboutme.setSelected(true);
        }
    }

    private void setFragmentTab(Fragment fragment) {
        /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.linear_parent, fragment).disallowAddToBackStack().commit();

        currentFragment = fragment;*/
    }

    @Override
    public void onClick(View v) {
        FRAGMENT_TYPE type = FRAGMENT_TYPE.enum_webbrowser;
        switch (v.getId()) {
            case R.id.collection_webbrowser:
                type = FRAGMENT_TYPE.enum_webbrowser;
                setFragmentTab(type.ordinal());
                break;
            case R.id.collection_other:
                type = FRAGMENT_TYPE.enum_other;
                setFragmentTab(type.ordinal());
                break;
            case R.id.collection_aboutme:
                type = FRAGMENT_TYPE.enum_aboutme;
                setFragmentTab(type.ordinal());
                break;
            case R.id.btn_back:
                finish();
                break;
            default:
                type = FRAGMENT_TYPE.enum_webbrowser;
                setFragmentTab(type.ordinal());
                break;
        }
    }
}
