package com.beauty.utils;

import android.widget.Toast;

import com.beauty.dialer.BeautyApplication;

public class ToastUitls {

    private static Toast sToast;

    private static Toast getToast(String msg) {
        if (sToast == null) {
            synchronized (ToastUitls.class) {
                if (sToast == null) {
                    sToast = Toast.makeText(BeautyApplication.getContext(), msg, Toast.LENGTH_SHORT);
                } else {
                    sToast.setText(msg);
                }
            }
        } else {
            sToast.setText(msg);
        }
        return sToast;
    }

    private static Toast getToast(int msgId) {
        if (sToast == null) {
            synchronized (ToastUitls.class) {
                if (sToast == null) {
                    sToast = Toast.makeText(BeautyApplication.getContext(), msgId, Toast.LENGTH_SHORT);
                } else {
                    sToast.setText(msgId);
                }
            }
        } else {
            sToast.setText(msgId);
        }
        return sToast;
    }

    public static void showToast(String msg) {
        getToast(msg).show();
    }

    public static void showToast(int msgId) {
        getToast(msgId).show();
    }

}
