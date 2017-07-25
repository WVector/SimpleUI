package com.vector.loadingpager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vector
 * on 2017/5/17 0017.
 */

public abstract class SimpleActivity extends AppCompatActivity implements SimplePager.RetryLoadDataListener, HeadView.OnBarMenuClickListener {

    private SimplePager mSimplePager;
    private Dialog mLoadingDialog;
    private long lastClick = 0;

    protected abstract void doBusiness();

    protected String getLeftMenuTextViewText() {
        return null;
    }

    protected int getLeftMenuImageViewResId() {
        return R.drawable.ic_arrow_back_black_24dp;
    }

    /**
     * 返回null的话显示SearchBar，否则显示标题，请注意
     */
    protected String getHeaderTitle() {
        return "";
    }

    protected int getRightMenuImageViewResId() {
        return -1;
    }

    protected String getRightMenuTextViewText() {
        return null;
    }

    protected void init(@NonNull Bundle bundle) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置通知栏
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            init(bundle);
        }
        View simple = View.inflate(this, R.layout.fragment_simple, null);
        setContentView(simple);

        HeadView headView = (HeadView) simple.findViewById(R.id.headView);

        headView.setHeaderTitle(getHeaderTitle())
                .setLeftMenuImageViewResId(getLeftMenuImageViewResId())
                .setLeftMenuTextViewText(getLeftMenuTextViewText())
                .setRightMenuImageViewResId(getRightMenuImageViewResId())
                .setRightMenuTextViewText(getRightMenuTextViewText())
                .addBarMenuClickListener(this)
                .init();

        headView.setVisibility(isShowHeader() ? View.VISIBLE : View.GONE);

        mSimplePager = (SimplePager) simple.findViewById(R.id.simple_pager);

        mSimplePager.addRetryLoadDataListener(this);

        // 子类的view都会添加在此mContentView中

        LayoutInflater.from(this).inflate(getLayoutResId(), mSimplePager.getContentView(), true);

        initView(mSimplePager.getContentView());
        doBusiness();
    }

    // 子类进行数据处理来决定处于哪一种状态
    public void show(SimplePager.ResultType resultType) {
        show(resultType, null);
    }

    public void show(SimplePager.ResultType resultType, String msg) {
        if (mSimplePager != null) {
            mSimplePager.show(resultType, msg);
        }
    }

    protected void showProgressDialog() {
        this.showProgressDialog(null, false, null);
    }

    protected void showProgressDialog(String msg) {
        this.showProgressDialog(msg, false, null);
    }

    protected void showProgressDialog(DialogInterface.OnCancelListener listener) {
        this.showProgressDialog(null, false, listener);
    }

    protected void showProgressDialog(String msg, Boolean cancelable, DialogInterface.OnCancelListener listener) {
        if (this.isFinishing()) {
            return;
        }

        mLoadingDialog = null;
        mLoadingDialog = LoadingDialogUtil.createLoadingDialog(this, msg, listener);
        mLoadingDialog.setCancelable(cancelable);


        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }

    }

    protected void hiddenProgressDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.cancel();
            mLoadingDialog = null;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    protected boolean isShowHeader() {
        return true;
    }

    protected abstract
    @LayoutRes
    int getLayoutResId();


    protected abstract void initView(ViewGroup contentView);

    @Override
    public void onRetryLoadData() {

    }

    @Override
    public void OnRightMenuClick() {

    }

    @Override
    public void OnLeftMenuClick() {
        this.finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }

    }

    protected boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }
}
