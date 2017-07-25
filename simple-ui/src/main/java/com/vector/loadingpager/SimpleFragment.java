package com.vector.loadingpager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class SimpleFragment extends LazyFragment implements HeadView.OnBarMenuClickListener, SimplePager.RetryLoadDataListener {


    public Bundle mBundle;
    private boolean isInit = false;
    private SimplePager mSimplePager;
    private Dialog mLoadingDialog;

    public Bundle getBundle() {
        return mBundle;
    }
    //子类的布局
//    private ViewGroup mFragmentView;

//    public FragmentActivity getContext() {
//        return mContext;
//    }

//    private FragmentActivity mContext;

    protected abstract void initView(ViewGroup contentView);

    protected abstract void doBusiness();

    protected String getLeftMenuTextViewText() {
        return null;
    }

    protected int getLeftMenuImageViewResId() {
        return -1;
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


    protected boolean isLazy() {
        return true;
    }

    @Override
    public void onRetryLoadData() {

    }

//    public View findViewById(int id) {
//        if (mFragmentView != null)
//            return mFragmentView.findViewById(id);
//        return null;
//    }

//    public void setFragmentView(int layoutResID) {
//        setFragmentView((ViewGroup) LayoutInflater.from(getActivity()).inflate(layoutResID, mContentViewGroup, false));
//    }
//
//    public void setFragmentView(ViewGroup viewGroup) {
//        mFragmentView = viewGroup;
////    }
//
//    public View getFragmentView() {
//        return mFragmentView;
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activity与Fragment之间的通信
        //一定
        mBundle = getArguments();
//        mContext = getActivity();


    }

    protected abstract
    @LayoutRes
    int getLayoutResId();

    @Override
    public View onCreateViewLazy(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HeadView headView = (HeadView) view.findViewById(R.id.headView);

        headView.setHeaderTitle(getHeaderTitle())
                .setLeftMenuImageViewResId(getLeftMenuImageViewResId())
                .setLeftMenuTextViewText(getLeftMenuTextViewText())
                .setRightMenuImageViewResId(getRightMenuImageViewResId())
                .setRightMenuTextViewText(getRightMenuTextViewText())
                .addBarMenuClickListener(this)
                .init();

        headView.setVisibility(isShowHeader() ? View.VISIBLE : View.GONE);

        mSimplePager = (SimplePager) view.findViewById(R.id.simple_pager);

        mSimplePager.addRetryLoadDataListener(this);

        LayoutInflater.from(getActivity()).inflate(getLayoutResId(), mSimplePager.getContentView(), true);

        bindView(mSimplePager.getContentView());

        initView(mSimplePager.getContentView());
    }

    protected void bindView(ViewGroup contentView) {

    }

    protected boolean isShowHeader() {
        return true;
    }

    @Override
    protected void initDataLazy() {
        if (!isLazy()) {
            return;
        }
        doBusiness();
        isInit = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!isLazy()) {
            doBusiness();
            isInit = true;
        }
    }
//
//    public void loadData() {
//        Log.e(this.getClass().getSimpleName(), "loadData...");
//        if (isInit) {
//            onRefreshData();
//        }
//    }
//
//    /**
//     *
//     */
//    public void onRefreshData() {
//
//    }

    protected void showProgressDialog() {
        this.showProgressDialog(null, false, null);
    }

    protected void showProgressDialog(String msg) {
        this.showProgressDialog(msg, false, null);
    }

    protected void showProgressDialog(String msg, DialogInterface.OnCancelListener listener) {
        this.showProgressDialog(msg, false, listener);
    }

    protected void showProgressDialog(DialogInterface.OnCancelListener listener) {
        this.showProgressDialog(null, false, listener);
    }

    protected void showProgressDialog(String msg, Boolean cancelable, DialogInterface.OnCancelListener listener) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }

        mLoadingDialog = null;
        mLoadingDialog = LoadingDialogUtil.createLoadingDialog(getActivity(), msg, listener);
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
    public void OnRightMenuClick() {

    }

    @Override
    public void OnLeftMenuClick() {

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    public boolean isInit() {
        return isInit;
    }
}
