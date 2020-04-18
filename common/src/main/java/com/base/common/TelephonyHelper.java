package com.base.common;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephonyHelper {

    private static TelephonyHelper sInstance = new TelephonyHelper();

    public static TelephonyHelper getInstance(){
        return sInstance;
    }

    private Context mContext;
    private TelephonyManager mTelephonyMgr;

    public TelephonyHelper(){
        mContext = GlobalEvn.getAppContext();
        mTelephonyMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public int getSimState(int slotIndex) {
        return mTelephonyMgr.getSimState(slotIndex);
    }

}
