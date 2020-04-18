package com.base.common;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.TelecomManager;

import androidx.core.app.ActivityCompat;

public class TelecomHelper {
    private static TelecomHelper sInstance = new TelecomHelper();

    public static TelecomHelper getInstance() {
        return sInstance;
    }

    private Context mContext;
    private TelecomManager mTelecomMgr;

    public TelecomHelper() {
        mContext =GlobalEvn.getAppContext();
        mTelecomMgr = (TelecomManager) mContext.getSystemService(Context.TELECOM_SERVICE);
    }

    public String getDefaultDialer() {
        return mTelecomMgr.getDefaultDialerPackage();
    }

    public String getSystemDialer() {
        return mTelecomMgr.getSystemDialerPackage();
    }

    public void placeCall(Uri address, Bundle extras) {
        mTelecomMgr.placeCall(address, extras);
    }
}
