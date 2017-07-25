package com.vector.loadingpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vector on 2016/8/24 0024.
 */
public class SettingItemView extends FrameLayout {

    private ImageView mIvIcon;
    private TextView mTvText;
    private View mLineUp;
    private View mLineDown;
    private TextView mTvSubText;

    public SettingItemView(Context context) {
        this(context, null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        View.inflate(getContext(), R.layout.layout_setting_item, this);

        mIvIcon = (ImageView) findViewById(R.id.iv_icon);
        mTvText = (TextView) findViewById(R.id.tv_text);
        mTvSubText = (TextView) findViewById(R.id.tv_sub_text);
        mLineUp = findViewById(R.id.line_up);
        mLineDown = findViewById(R.id.line_down);

        initAttr(attrs);

    }


    private void initAttr(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SettingItemView);
        String text = typedArray.getString(R.styleable.SettingItemView_item_text);
        String subText = typedArray.getString(R.styleable.SettingItemView_item_sub_text);
//        Drawable iconDrawable = typedArray.getDrawable(R.styleable.SettingItemView_item_icon);
        int iconResId = typedArray.getResourceId(R.styleable.SettingItemView_item_icon_resId, R.drawable.ic_keyboard_arrow_right_black_24dp);
        boolean showUp = typedArray.getBoolean(R.styleable.SettingItemView_line_up, true);
        boolean showDown = typedArray.getBoolean(R.styleable.SettingItemView_line_down, true);
        int textColor = typedArray.getColor(R.styleable.SettingItemView_item_text_color, Color.parseColor("#282828"));
        int lineColor = typedArray.getColor(R.styleable.SettingItemView_line_color, Color.parseColor("#282828"));
        typedArray.recycle();

        setSubText(subText);
        setTextColor(textColor);
        setLineColor(lineColor);
        setTitle(text);
//        setIcon(iconDrawable);
        setIcon(iconResId);

        isShowLineUp(showUp);
        isShowLineDown(showDown);
    }

    private void setSubText(String subText) {
        if (subText != null) {
            mTvSubText.setText(subText);
        }
    }

    private void setLineColor(int lineColor) {
        mLineDown.setBackgroundColor(lineColor);
        mLineUp.setBackgroundColor(lineColor);
    }

    private void setTextColor(int textColor) {
        mTvText.setTextColor(textColor);
    }

    public void isShowLineUp(boolean showUp) {
        mLineUp.setVisibility(showUp ? View.VISIBLE : View.INVISIBLE);
    }

    public void isShowLineDown(boolean showDown) {
        mLineDown.setVisibility(showDown ? View.VISIBLE : View.INVISIBLE);
    }

    public String getTitle() {
        if (mTvText != null) {
            return mTvText.getText().toString();
        }
        return "";
    }

    public void setTitle(String text) {
        if (text != null) {
            mTvText.setText(text);
        }
    }

    public void setIcon(int resId) {
        mIvIcon.setImageResource(resId);
    }

    public void setIcon(Drawable iconDrawable) {
        if (iconDrawable != null) {
            mIvIcon.setImageDrawable(iconDrawable);
        }
    }
}
