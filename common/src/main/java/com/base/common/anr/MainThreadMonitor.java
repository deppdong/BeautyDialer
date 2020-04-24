package com.base.common.anr;


import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 *
 */
public class MainThreadMonitor {
    private final static String TAG = "MainThreadMonitor";
    private final static int MAIN_THREAD_CHECK_DURATION = 2000;

    private final static int MSG_GET_MAIN_TRACE = 1;

    private Handler mBgHandler;
    private HandlerThread mHandlerThread;

    private Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

    public MainThreadMonitor() {
    }

    public void start() {
        if (mHandlerThread == null) {
            mHandlerThread = new HandlerThread("anr_checker");
            mHandlerThread.start();

            mBgHandler = new Handler() {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case MSG_GET_MAIN_TRACE:
                            dumpMainThreadTrace();
                            break;
                        default:
                            break;
                    }
                }
            };
        }
    }

    public void stop(){
        if (mHandlerThread != null) {
            mBgHandler = null;
            mHandlerThread.quitSafely();
            mHandlerThread = null;
        }
    }

    private void dumpMainThreadTrace() {
        Thread mainThread = mMainThreadHandler.getLooper().getThread();
        StackTraceElement[] stackTraceElements =  mainThread.getStackTrace();
        if (stackTraceElements != null && stackTraceElements.length >0) {
            final int max = 10;
            int count = 0;
            StringBuilder trace = new StringBuilder("MainThreadTrace ui thread may be blocked here:");
            for (StackTraceElement element : stackTraceElements) {
                trace.append(element.toString()).append("\t");
                if(++count >max) {
                    break;
                }
            }
            if (trace.length() >0) {
                Log.d(TAG, trace.toString());
            }
        }
    }

}
