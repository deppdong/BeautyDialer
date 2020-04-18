package com.base.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import androidx.annotation.NonNull;

public class AsyncHandler {

    private Handler mHandler;
    private HandlerThread mHalderThread;
    public AsyncHandler () {
        mHalderThread = new HandlerThread("async_handler");
        mHalderThread.start();
        mHandler = new Handler(mHalderThread.getLooper()) {
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        };
    }
}
