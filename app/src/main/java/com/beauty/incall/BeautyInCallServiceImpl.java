package com.beauty.incall;

import android.content.Intent;
import android.os.IBinder;
import android.telecom.CallAudioState;
import android.telecom.InCallService;
import android.util.Log;

import com.beauty.incall.audio.CallAudioManager;

/**
 * 接收Telecom电话状态变化
 */
public class BeautyInCallServiceImpl extends InCallService {

    @Override
    public void onCallAudioStateChanged(CallAudioState audioState) {
        CallAudioManager.getInstance().onAudioStateChanged(audioState.isMuted(),
            audioState.getRoute(), audioState.getSupportedRouteMask());
    }

    @Override
    public void onBringToForeground(boolean showDialpad) {
        InCallPresenter presenter = InCallPresenter.getInstance();
        presenter.onBringToForeground(showDialpad);
    }

    @Override
    public void onCallAdded(android.telecom.Call call) {
//        presenter.onCallAdded(TelecomCall.getProxy(call));
    }

    @Override
    public void onCallRemoved(android.telecom.Call call) {
    }

    @Override
    public void onCanAddCallChanged(boolean canAddCall) {
        InCallPresenter presenter = InCallPresenter.getInstance();
        if(presenter == null) {
            return;
        }
        presenter.onCanAddCallChanged(canAddCall);
    }

    @Override
    public IBinder onBind(Intent intent) {
        BeautyTelecomAdapter.getInstance().setInCallService(this);
        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        super.onUnbind(intent);
        BeautyTelecomAdapter.getInstance().setInCallService(null);
        return false;
    }
}
