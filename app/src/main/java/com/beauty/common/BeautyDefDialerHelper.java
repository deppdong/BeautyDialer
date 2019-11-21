package com.beauty.common;

import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;

import com.beauty.common.BeautyGlobal;
import com.beauty.dialer.BeautyApplication;
import com.beauty.utils.DLog;
import com.beauty.utils.ToastUitls;

public class BeautyDefDialerHelper {

    private String mDefDialerPkg;

    BeautyDefDialerHelper() {
    }

    public void checkDefDialerPkg() {
        mDefDialerPkg = BeautyApplication.getTelecomManager().getDefaultDialerPackage();
    }

    public String getDefDialerPkg() {
        return mDefDialerPkg;
    }

    /**
     * 弹出Telecom#ChangeDefaultDialerDialg的有2个条件：
     * <p>Manifest中有申明InCallService且有BIND_INCALL_SERVICE权限</p>
     * <p>Manifest中有申明拨号的Activity（<action android:name="android.intent.action.DIAL" />）</p>
     * @param context
     */
    public static void requestChangeDefDialer(Context context, String packageName) {
        if (context == null) {
            return;
        }

        String curDefDialer = BeautyGlobal.getInstance().getDefDialerHelper().getDefDialerPkg();
        if (packageName.equals(curDefDialer)) {
            ToastUitls.showToast("已设置为默认拨号盘");
            return;
        }

        DLog.logDialer("requestChangeDefDialer " + packageName);
        Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER);
        intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            DLog.trace("requestSelfAsDefDialer", e);
        }
    }
}
