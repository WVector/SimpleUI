package com.vector.loadingpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vector
 * on 2017/5/25 0025.
 */

public class LifecycleFragment extends Fragment {
    private static final boolean DEBUG = true;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onAttach...");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onCreate...");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onCreateView...");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onViewCreated...");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onActivityCreated...");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onStart...");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onResume...");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onPause...");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onStop...");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onDestroyView...");
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onDestroy...");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (DEBUG) {
            Log.e(this.getClass().getSimpleName(), "onDetach...");
        }
    }


}
