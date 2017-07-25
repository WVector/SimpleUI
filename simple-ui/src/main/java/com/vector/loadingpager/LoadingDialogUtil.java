package com.vector.loadingpager;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by admin on 2017/3/13.
 */

public class LoadingDialogUtil {
    /**
     * 有取消回调的进度dialog
     *
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Activity context, String msg, DialogInterface.OnCancelListener listener) {
        final Dialog dialog = new Dialog(context, R.style.NoBackGroundDialog);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        if (listener != null) dialog.setOnCancelListener(listener);
        Window window = dialog.getWindow();
        assert window != null;
        window.setGravity(Gravity.CENTER);
//        int width = getWidth(context) * 2 / 3;
        window.setLayout(android.view.WindowManager.LayoutParams.WRAP_CONTENT, android.view.WindowManager.LayoutParams.WRAP_CONTENT);
        View view = context.getLayoutInflater().inflate(R.layout.loading_dialog, (ViewGroup)window.getDecorView(),true);

        TextView tipTextView = (TextView) view.findViewById(R.id.tipTextView);// 提示文字
        if (!TextUtils.isEmpty(msg)) {
            tipTextView.setText(msg);// 设置加载信息
        }

//        window.setContentView(view);//
        return dialog;

    }

    private static int getWidth(Activity context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

}
