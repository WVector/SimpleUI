package com.vector.simpleuidemo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.vector.loadingpager.SimpleActivity;
import com.vector.loadingpager.SimplePager;

public class MainActivity extends SimpleActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnAdapterChangeListener, ViewPager.OnPageChangeListener {

    private MenuItem menuItem;

    @Override
    protected boolean isShowHeader() {
        return false;
    }

    private BottomNavigationView mNavigation;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("MainActivity", "item.getGroupId():" + item.getGroupId());
        Log.d("MainActivity", "item.getOrder():" + item.getOrder());
        Log.d("MainActivity", "item.getItemId():" + item.getItemId());
        switch (item.getItemId()) {
            case R.id.navigation_home:
                mViewPager.setCurrentItem(0, false);
                return true;
            case R.id.navigation_dashboard:
                mViewPager.setCurrentItem(1, false);
                return true;
            case R.id.navigation_notifications:
                mViewPager.setCurrentItem(2, false);
                return true;
            case R.id.navigation_info:
                mViewPager.setCurrentItem(3, false);
                return true;
        }
        return false;
    }

    private ViewPager mViewPager;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(ViewGroup contentView) {
        mNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        mViewPager = (ViewPager) findViewById(R.id.vp_main);

        BottomNavigationViewHelper.disableShiftMode(mNavigation);

        mNavigation.setOnNavigationItemSelectedListener(this);
        mViewPager.removeOnPageChangeListener(this);
        mViewPager.addOnPageChangeListener(this);


        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));


        show(SimplePager.ResultType.RESULT);
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            mNavigation.getMenu().getItem(0).setChecked(false);
        }
        menuItem = mNavigation.getMenu().getItem(position);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
