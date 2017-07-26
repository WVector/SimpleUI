package com.vector.simpleuidemo;

import android.util.SparseArray;

import com.vector.loadingpager.SimpleFragment;

public class FragmentFactory {
    public static final int NUMBER = 4;

    private static SparseArray<SimpleFragment> mFragments = new SparseArray<SimpleFragment>();

    public static SimpleFragment createFragment(int position) {
        SimpleFragment fragment;
        fragment = mFragments.get(position);
        if (fragment == null) {
            fragment = getSimpleFragment(position);
            if (fragment != null) {
                mFragments.put(position, fragment);
            }
        }
        return fragment;

    }

    private static SimpleFragment getSimpleFragment(int position) {
        if (position == 0) {
            return new Fragment1();
        } else if (position == 1) {
            return new Fragment2();
        } else if (position == 2) {
            return new Fragment3();
        } else if (position == 3) {
            return new Fragment4();
        }
        return null;
    }

    public static void clear() {
        if (mFragments != null) {
            mFragments.clear();
        }
    }
}
