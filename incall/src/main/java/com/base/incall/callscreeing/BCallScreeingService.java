package com.base.incall.callscreeing;

import android.content.Intent;
import android.os.IBinder;
import android.telecom.Call;
import android.telecom.CallScreeningService;
import android.util.Log;

import androidx.annotation.NonNull;

import com.base.common.DLog;

/**
 * 来电时候的来电拦截功能(Telecom#CallsManager#IncomingCallFilter)
 *
 * @see Call
 */
public class BCallScreeingService extends CallScreeningService {
    private static final String TAG = "BCallScreeingService";

    private CallResponse mCallResponse;

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
        callDetails.getAccountHandle();
        callDetails.getHandle();
        // 如果視頻彩鈴，則
        CallResponse response = new CallResponse.Builder().setSilenceCall(true).build();
        // 靜音
        respondToCall(callDetails, response);
        Log.d(TAG, "onScreenCall: " + response.toString());
    }
}
