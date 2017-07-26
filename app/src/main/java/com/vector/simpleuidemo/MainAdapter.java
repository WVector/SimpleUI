package com.vector.simpleuidemo;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;


/**
 * Created by fengjunming_t on 2016/6/22 0022.
 */
public class MainAdapter extends FragmentStatePagerAdapter {


    private static final String TAG = MainAdapter.class.getSimpleName();


    public MainAdapter(@NonNull FragmentActivity activity) {
        super(activity.getSupportFragmentManager());

    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return FragmentFactory.NUMBER;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem() called with: position = [" + position + "]");
        return FragmentFactory.createFragment(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d(TAG, "destroyItem() called with:  position = [" + position + "], object = [" + object + "]");
//        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem() called with:  position = [" + position + "]");
        return super.instantiateItem(container, position);
    }

}
