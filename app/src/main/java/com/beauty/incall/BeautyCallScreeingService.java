package com.beauty.incall;

import android.content.Intent;
import android.os.IBinder;
import android.telecom.Call;
import android.telecom.CallScreeningService;

import androidx.annotation.NonNull;

/**
 * 来电时候的来电拦截功能(Telecom#CallsManager#IncomingCallFilter)
 * @see Call
 */
public class BeautyCallScreeingService extends CallScreeningService {

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onScreenCall(@NonNull Call.Details callDetails) {

    }
}
