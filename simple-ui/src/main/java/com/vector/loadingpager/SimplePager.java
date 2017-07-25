package com.vector.loadingpager;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Vector
 * on 2017/5/16 0016.
 */

public class SimplePager extends FrameLayout {
    private View mLoadingView;
    private View mFailedView;
    private View mEmptyView;
    private ViewGroup mContentView;
    //提示语
    private TextView mLoadingMsgTextView;
    private TextView mFailedMsgTextView;
    private TextView mEmptyMsgTextView;
    private RetryLoadDataListener mRetryLoadDataListener;

    public SimplePager(@NonNull Context context) {
        this(context, null);
    }

    public SimplePager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimplePager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private View getLoadingView() {
        //加载界面
        if (mLoadingView == null) {
            mLoadingView = findViewById(R.id.base_loading);
            initLoadingView();
        }
        return mLoadingView;
    }

    private View getFailedView() {
        //失败界面
        if (mFailedView == null) {
            mFailedView = findViewById(R.id.base_failed);
            initFailedView();
        }
        return mFailedView;
    }

    private View getEmptyView() {
        //空界面
        if (mEmptyView == null) {
            mEmptyView = findViewById(R.id.base_empty);
            initEmptyView();
        }
        return mEmptyView;
    }

    public ViewGroup getContentView() {
        //加载的内容界面
        if (mContentView == null) {
            mContentView = (ViewGroup) findViewById(R.id.base_content);
        }
        return mContentView;
    }

    public RetryLoadDataListener getRetryLoadDataListener() {
        return mRetryLoadDataListener;
    }

    public void addRetryLoadDataListener(RetryLoadDataListener retryLoadDataListener) {
        mRetryLoadDataListener = retryLoadDataListener;
    }

    private void init() {
        initView();
        initData();
    }

    private void initData() {
        //默认
        show(ResultType.LOADING);
    }

    public SimplePager addContentView(View view) {
        if (mContentView != null) {
            LayoutParams glp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            view.setLayoutParams(glp);
            mContentView.addView(view);
        }
        return this;
    }

    private void initView() {
        View.inflate(getContext(), R.layout.pager_simple, this);
        getLoadingView();
        getFailedView();
        getEmptyView();
        getContentView();
    }

    private void initEmptyView() {
//        ImageView emptyImageView = (ImageView) mEmptyView.findViewById(R.id.iv_empty_pic);
        mEmptyMsgTextView = (TextView) mEmptyView.findViewById(R.id.tv_empty_msg);
//        RelativeLayout emptyLayout = (RelativeLayout) mEmptyView.findViewById(R.id.empty_layout);
        Button emptyRetryButton = (Button) mEmptyView.findViewById(R.id.btn_retry);
        emptyRetryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                retryData(true);
            }
        });
    }

    private void initFailedView() {
//        ImageView failedImageView = (ImageView) mFailedView.findViewById(R.id.iv_failed_pic);
        mFailedMsgTextView = (TextView) mFailedView.findViewById(R.id.tv_failed_msg);
//        RelativeLayout failedLayout = (RelativeLayout) mFailedView.findViewById(R.id.failed_layout);
        Button failedRetryButton = (Button) mFailedView.findViewById(R.id.btn_retry);
        failedRetryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                retryData(true);
            }


        });
    }

    private void initLoadingView() {
        mLoadingMsgTextView = (TextView) mLoadingView.findViewById(R.id.tv_loading_msg);
    }

    private void retryData(boolean b) {
        if (getRetryLoadDataListener() != null) {
            getRetryLoadDataListener().onRetryLoadData();
        }

    }

    public void show(ResultType resultType) {
        show(resultType, null);
    }

    // 子类进行数据处理来决定处于哪一种状态
    public void show(ResultType resultType, @Nullable String msg) {

        switch (resultType) {
            case LOADING:
                showLoading(msg);
                break;
            case EMPTY:
                showEmpty(msg);
                break;
            case FAILED:
                showFailed(msg);
                break;
            case RESULT:
                showResult();
                break;
            default:
                showLoading(msg);
                break;

        }
    }

    private void showLoading(@Nullable String msg) {
        getLoadingView().setVisibility(View.VISIBLE);
        getFailedView().setVisibility(View.GONE);
        getContentView().setVisibility(View.GONE);
        getEmptyView().setVisibility(View.GONE);


        if (!TextUtils.isEmpty(msg)) {
            mLoadingMsgTextView.setText(msg);
        } else {
            //		    mLoadingMsgTextView.setTitle("数据为空");
        }
        mLoadingMsgTextView.setVisibility(View.VISIBLE);
    }

    private void showEmpty(@Nullable String msg) {
        getLoadingView().setVisibility(View.GONE);
        getContentView().setVisibility(View.GONE);
        getFailedView().setVisibility(View.GONE);
        getEmptyView().setVisibility(View.VISIBLE);


        if (!TextUtils.isEmpty(msg)) {
            mEmptyMsgTextView.setText(msg);
        } else {
            //		    mEmptyMsgTextView.setTitle("数据为空");
        }

        mEmptyMsgTextView.setVisibility(View.VISIBLE);
    }

    private void showFailed(@Nullable String msg) {
        getLoadingView().setVisibility(View.GONE);
        getContentView().setVisibility(View.GONE);
        getEmptyView().setVisibility(View.GONE);
        getFailedView().setVisibility(View.VISIBLE);


        if (!TextUtils.isEmpty(msg)) {
            mFailedMsgTextView.setText(msg);

        } else {
            //		    mFailedMsgTextView.setTitle("数据加载失败");
        }
        mFailedMsgTextView.setVisibility(View.VISIBLE);
    }

    private void showResult() {
        getLoadingView().setVisibility(View.GONE);
        getFailedView().setVisibility(View.GONE);
        getEmptyView().setVisibility(View.GONE);
        getContentView().setVisibility(View.VISIBLE);
    }

    public enum ResultType {
        LOADING, RESULT, EMPTY, FAILED
    }

    public interface RetryLoadDataListener {
        void onRetryLoadData();
    }
}
