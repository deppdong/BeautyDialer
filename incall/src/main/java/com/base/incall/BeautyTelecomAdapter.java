package com.base.incall;

import android.telecom.InCallService;

public class BeautyTelecomAdapter {

    private static BeautyTelecomAdapter sInstance = new BeautyTelecomAdapter();

    public static BeautyTelecomAdapter getInstance() {
        return sInstance;
    }

    private InCallService mInCallService;

    public void setInCallService(InCallService inCallService) {
        mInCallService = inCallService;
    }
}
