package com.vector.simpleuidemo;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vector.loadingpager.SimpleFragment;
import com.vector.loadingpager.SimplePager;

/**
 * Created by Vector
 * on 2017/7/25 0025.
 */

public abstract class TestFragment extends SimpleFragment {

    @Override
    protected boolean isLazy() {
        return true;
    }

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
        Button button = (Button) contentView.findViewById(R.id.btn_clear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentFactory.clear();
            }
        });


    }

    @Override
    protected void doBusiness() {
//        String name = getBundle().getString("name", "");

//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });


        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mNameTv.setText(getName());
                show(SimplePager.ResultType.RESULT);
            }
        }, 3000);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_test;
    }


    public abstract CharSequence getName();
}
