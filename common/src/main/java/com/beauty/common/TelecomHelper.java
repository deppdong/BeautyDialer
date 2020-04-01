package com.beauty.common;

import android.content.Context;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;

public class TelecomHelper {
    private static TelecomHelper sInstance = new TelecomHelper();

    public static TelecomHelper getInstance(){
        return  sInstance;
    }

    private TelecomManager mTelecomMgr;

    public TelecomHelper(){
        mTelecomMgr = (TelecomManager) GlobalEvn.getAppContext().getSystemService(Context.TELECOM_SERVICE);
    }

    public String getDefaultDialer() {
       return mTelecomMgr.getDefaultDialerPackage();
    }

    private TelecomManager getTelecomManager() {
        if (mTelecomMgr == null) {
            mTelecomMgr = (TelecomManager) GlobalEvn.getAppContext().getSystemService(Context.TELECOM_SERVICE);
        }
        return mTelecomMgr;
    }
}
