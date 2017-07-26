package com.vector.simpleuidemo;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

/**
 * Created by fengjunming_t on 2016/6/22 0022.
 */
public class MainAdapter extends FragmentStatePagerAdapter {

    public static final int NUMBER = 4;

    public MainAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);

    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return NUMBER;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
