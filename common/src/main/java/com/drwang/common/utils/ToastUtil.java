package com.drwang.common.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ToastUtil {
    private static Toast toast = null;

    public static final int SHOW = 0;
    public static final int LONG = 1;

    public static void showToast(Context context, String msg) {
        if (msg == null || "".equals(msg)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void showToast(Fragment fragment, String msg) {
        if (msg == null || "".equals(msg)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(fragment.getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void showToast(Context context, String msg, int time) {
        if (msg == null || "".equals(msg)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), msg, time);
        } else {
            toast.setText(msg);
            toast.setDuration(time);
        }
        toast.show();
    }

    public static void showToast(Context context, int resid, int length) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), resid, length);
        } else {
            toast.setText(context.getApplicationContext().getResources().getText(resid));
            toast.setDuration(length);
        }
        toast.show();
    }

    /**
     * 取消弹出toast
     */
    public static void cancleToast(Context context) {
        if (context != null) {
            if (toast != null) {
                toast.cancel();
            }
        }
    }
}
