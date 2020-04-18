package com.base.common;

import android.widget.Toast;

public class ToastUtils {
    private static Toast sToast;

    public static void showToast(String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(GlobalEvn.getAppContext(), msg, Toast.LENGTH_LONG);
        } else {
            sToast.setText(msg);
        }
        sToast.show();
    }

    public static void showToast(int resId) {
        if (sToast == null) {
            sToast = Toast.makeText(GlobalEvn.getAppContext(), resId, Toast.LENGTH_LONG);
        } else {
            sToast.setText(resId);
        }
        sToast.show();
    }
}
