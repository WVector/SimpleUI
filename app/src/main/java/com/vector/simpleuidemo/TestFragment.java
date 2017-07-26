package com.vector.simpleuidemo;

import android.view.ViewGroup;
import android.widget.TextView;

import com.vector.loadingpager.SimpleFragment;
import com.vector.loadingpager.SimplePager;

/**
 * Created by Vector
 * on 2017/7/25 0025.
 */

public abstract class TestFragment extends SimpleFragment {

    private CharSequence mName;

    @Override
    protected boolean isShowHeader() {
        return super.isShowHeader();
    }

    private TextView mNameTv;

//    public static TestFragment newInstance(String name) {
//        TestFragment testFragment = new TestFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("name", name);
//        testFragment.setArguments(bundle);
//        return testFragment;
//    }


    @Override
    protected void initView(ViewGroup contentView) {

        mNameTv = (TextView) contentView.findViewById(R.id.tv_name);


    }

    @Override
    protected void doBusiness() {
//        String name = getBundle().getString("name", "");
        mNameTv.setText(getName());
        show(SimplePager.ResultType.RESULT);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_test;
    }

    public abstract CharSequence getName();
}
