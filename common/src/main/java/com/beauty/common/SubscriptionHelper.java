package com.beauty.common;

import android.content.Context;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

public class SubscriptionHelper {
    private static SubscriptionHelper sInstance = new SubscriptionHelper();

    public static SubscriptionHelper getInstance(){
        return sInstance;
    }

    private SubscriptionManager mSubscriptionMgr;

    public SubscriptionHelper(){
    }

    private SubscriptionManager getSubscriptionManager() {
        if (mSubscriptionMgr == null) {
            mSubscriptionMgr = (SubscriptionManager) GlobalEvn.getAppContext().getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        }
        return mSubscriptionMgr;
    }
}
