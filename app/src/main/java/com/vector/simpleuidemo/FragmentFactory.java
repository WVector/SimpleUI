package com.vector.simpleuidemo;

import android.util.SparseArray;

import com.vector.loadingpager.SimpleFragment;

public class FragmentFactory {

    private static SparseArray<SimpleFragment> mFragments = new SparseArray<SimpleFragment>();

    public static SimpleFragment createFragment(int position) {
        SimpleFragment fragment = null;
        fragment = mFragments.get(position);
        if (fragment == null) {
            if (position == 0) {
                fragment = new Fragment1();
            } else if (position == 1) {
                fragment = new Fragment2();
            } else if (position == 2) {
                fragment = new Fragment3();
            } else if (position == 3) {
                fragment = new Fragment4();
            }
            if (fragment != null) {
                mFragments.put(position, fragment);
            }
        }
        return fragment;

    }

    public static void clear() {
        if (mFragments != null) {
            mFragments.clear();
        }
    }
}
