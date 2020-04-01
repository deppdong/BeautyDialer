package com.beauty.common;

import android.content.Context;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;

public class TelephonyHelper {

    private static TelephonyHelper sInstance = new TelephonyHelper();

    public static TelephonyHelper getInstance(){
        return sInstance;
    }

    private TelephonyManager mTelephonyMgr;

    public TelephonyHelper(){
    }

    private TelephonyManager getTelephonyManager() {
        if (mTelephonyMgr == null) {
            mTelephonyMgr = (TelephonyManager) GlobalEvn.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
        }
        return mTelephonyMgr;
    }
}
