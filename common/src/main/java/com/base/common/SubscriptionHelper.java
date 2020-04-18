package com.base.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;

import java.util.List;

public class SubscriptionHelper {
    private static SubscriptionHelper sInstance = new SubscriptionHelper();

    public static SubscriptionHelper getInstance(){
        return sInstance;
    }

    private Context mContext;
    private SubscriptionManager mSubscriptionMgr;
    private List<SubscriptionInfo> mSubInfos;

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    public SubscriptionHelper(){
        mContext = GlobalEvn.getAppContext();
        mSubscriptionMgr =  (SubscriptionManager) mContext.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        registerReceiver();
    }

    //========SIM 状态变化========
    private final static int MSG_REFRESH_SUBINFO = 1;
    private final static String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";
    private SimStateReceiver simStateReceiver;

    private void registerReceiver(){
        if (simStateReceiver == null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ACTION_SIM_STATE_CHANGED);
            simStateReceiver = new SimStateReceiver();

        }
    }

    private class SimStateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            final String action = intent.getAction();
            if (ACTION_SIM_STATE_CHANGED.equals(action)) {
                doRefreshSubCache();
            }
        }
    }

    private void doRefreshSubCache() {
        if (mHandlerThread == null) {
            mHandlerThread = new HandlerThread("async_sim");
            mHandlerThread.start();
            mHandler = new Handler(mHandlerThread.getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case MSG_REFRESH_SUBINFO:
                            if (mSubInfos== null) {
                                mSubInfos = mSubscriptionMgr.getActiveSubscriptionInfoList();
                            } else {
                                synchronized (mSubInfos) {
                                    mSubInfos = mSubscriptionMgr.getActiveSubscriptionInfoList();
                                }
                            }

                            break;
                        default:
                            break;
                    }
                }
            };
        }

        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_REFRESH_SUBINFO), 200);
    }

}
