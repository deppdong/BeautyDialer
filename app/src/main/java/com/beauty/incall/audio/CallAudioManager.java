package com.beauty.incall.audio;

/**
 * 通话声音的控制
 */
public class CallAudioManager {

    private static CallAudioManager sInstance = new CallAudioManager();

    public static CallAudioManager getInstance() {
        return sInstance;
    }

    public void onAudioStateChanged(boolean isMuted, int route, int supportedRoutes) {

    }
}
