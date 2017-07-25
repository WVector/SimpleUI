package com.vector.loadingpager;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Vector
 * on 2017/5/16 0016.
 */

public class HeadView extends FrameLayout implements View.OnClickListener {
    private String mHeaderTitle = "标题";
    private String mLeftMenuTextViewText;
    private int mLeftMenuImageViewResId = -1;
    private String mRightMenuTextViewText;
    private int mRightMenuImageViewResId = -1;
    @ColorRes
    private int mTextColor = -1;

    public HeadView(@NonNull Context context) {
        this(context, null);
    }

    public HeadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
    }

    public void init() {
        initView();
    }

    private void initView() {
        View.inflate(getContext(), R.layout.layout_simple_header, this);

        // Title设置
        TextView headerTitleTextView = (TextView) findViewById(R.id.tv_header_title);
        // 左右菜单
        TextView leftMenuTextView = (TextView) findViewById(R.id.tv_header_left_menu);
        TextView rightMenuTextView = (TextView) findViewById(R.id.tv_header_right_menu);
        ImageView leftMenuImageView = (ImageView) findViewById(R.id.iv_header_left_menu);
        ImageView rightMenuImageView = (ImageView) findViewById(R.id.iv_header_right_menu);

        if (getTextColor()!=-1){
            headerTitleTextView.setTextColor(getTextColor());
            leftMenuTextView.setTextColor(getTextColor());
            rightMenuTextView.setTextColor(getTextColor());
        }

        if (getHeaderTitle() == null) {
            RelativeLayout mSearchBarRelativeLayout = (RelativeLayout) findViewById(R.id.rl_search_bar);
            mSearchBarRelativeLayout.setVisibility(View.VISIBLE);
            headerTitleTextView.setVisibility(View.GONE);
            ImageView mSearchImageView = (ImageView) findViewById(R.id.iv_search_bar);
            EditText mSearchEditText = (EditText) findViewById(R.id.et_search_bar);
            mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    return false;
                }
            });
        } else {
            headerTitleTextView.setText(getHeaderTitle());
        }

        // 左侧Menu设置
        if (!TextUtils.isEmpty(getLeftMenuTextViewText())) {

            leftMenuTextView.setText(getLeftMenuTextViewText());
            leftMenuTextView.setVisibility(View.VISIBLE);
            leftMenuTextView.setOnClickListener(this);
            leftMenuImageView.setVisibility(View.GONE);
        } else {
            leftMenuTextView.setVisibility(View.GONE);
        }
        // 左侧图形按钮
        if (getLeftMenuImageViewResId() != -1) {
            leftMenuImageView.setImageResource(getLeftMenuImageViewResId());
            leftMenuImageView.setVisibility(View.VISIBLE);
            leftMenuTextView.setVisibility(View.GONE);
            leftMenuImageView.setOnClickListener(this);
        } else {
            leftMenuImageView.setVisibility(View.GONE);
        }
        //右侧菜单文字
        if (!TextUtils.isEmpty(getRightMenuTextViewText())) {

            rightMenuTextView.setText(getRightMenuTextViewText());
            rightMenuTextView.setVisibility(View.VISIBLE);
            rightMenuImageView.setVisibility(View.GONE);
            rightMenuTextView.setOnClickListener(this);
        } else {
            rightMenuTextView.setVisibility(View.GONE);
        }

        // 右侧Menu设置
        if (getRightMenuImageViewResId() != -1) {
            rightMenuImageView.setImageResource(getRightMenuImageViewResId());
            rightMenuImageView.setVisibility(View.VISIBLE);
            rightMenuTextView.setVisibility(View.GONE);
            rightMenuImageView.setOnClickListener(this);

        } else {
            rightMenuImageView.setVisibility(View.GONE);
        }

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_header_left_menu || i == R.id.iv_header_left_menu) {
            if (getBarMenuClickListener() != null) {
                getBarMenuClickListener().OnLeftMenuClick();
            }

        } else if (i == R.id.iv_header_right_menu || i == R.id.tv_header_right_menu) {
            if (getBarMenuClickListener() != null) {
                getBarMenuClickListener().OnRightMenuClick();
            }

        } else {
        }
    }

    public OnBarMenuClickListener getBarMenuClickListener() {
        return mBarMenuClickListener;
    }

    public HeadView addBarMenuClickListener(OnBarMenuClickListener barMenuClickListener) {
        mBarMenuClickListener = barMenuClickListener;
        return this;
    }

    private OnBarMenuClickListener mBarMenuClickListener = null;

    public String getHeaderTitle() {
        return mHeaderTitle;
    }

    public HeadView setHeaderTitle(String headerTitle) {
        mHeaderTitle = headerTitle;
        return this;
    }

    public String getLeftMenuTextViewText() {
        return mLeftMenuTextViewText;
    }

    public HeadView setLeftMenuTextViewText(String leftMenuTextViewText) {
        mLeftMenuTextViewText = leftMenuTextViewText;
        return this;
    }

    public int getLeftMenuImageViewResId() {
        return mLeftMenuImageViewResId;
    }

    public HeadView setLeftMenuImageViewResId(int leftMenuImageViewResId) {
        mLeftMenuImageViewResId = leftMenuImageViewResId;
        return this;
    }

    public String getRightMenuTextViewText() {
        return mRightMenuTextViewText;
    }

    public HeadView setRightMenuTextViewText(String rightMenuTextViewText) {
        mRightMenuTextViewText = rightMenuTextViewText;
        return this;
    }

    public int getRightMenuImageViewResId() {
        return mRightMenuImageViewResId;
    }

    public HeadView setRightMenuImageViewResId(int rightMenuImageViewResId) {
        mRightMenuImageViewResId = rightMenuImageViewResId;
        return this;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }


    public interface OnBarMenuClickListener {
        void OnRightMenuClick();

        void OnLeftMenuClick();
    }

}
