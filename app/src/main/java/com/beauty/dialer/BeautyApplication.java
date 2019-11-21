package com.beauty.dialer;

import android.app.Application;
import android.content.Context;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

import com.beauty.common.BeautyGlobal;

public class BeautyApplication extends Application {

    private static BeautyApplication sInstance;

    private TelecomManager mTelecomManasger;
    private TelephonyManager mTelephonyManager;
    private SubscriptionManager mSubscriptionManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        BeautyGlobal.getInstance().initAsync();
    }

    public static TelecomManager getTelecomManager() {
        if (sInstance.mTelecomManasger == null) {
            sInstance.mTelecomManasger = (TelecomManager) sInstance.getSystemService(Context.TELECOM_SERVICE);
        }
        return sInstance.mTelecomManasger;
    }

    public static TelephonyManager getTelephonyManager() {
        if (sInstance.mTelephonyManager == null) {
            sInstance.mTelephonyManager = (TelephonyManager) sInstance.getSystemService(Context.TELEPHONY_SERVICE);
        }
        return sInstance.mTelephonyManager;
    }

    public static SubscriptionManager getSubscriptionManager() {
        if (sInstance.mSubscriptionManager == null) {
            sInstance.mSubscriptionManager = (SubscriptionManager) sInstance.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        }
        return sInstance.mSubscriptionManager;
    }

    public static Context getContext() {
        return sInstance;
    }

}
