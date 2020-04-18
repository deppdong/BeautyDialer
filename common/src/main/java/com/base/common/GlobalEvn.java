package com.base.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;

public class GlobalEvn {

    private static GlobalEvn sInstance = new GlobalEvn();

    public static GlobalEvn getInstance() {
        return sInstance;
    }

    private Context mContext;
    public void init(Context context) {
        mContext = context.getApplicationContext();
        TelecomHelper.getInstance();
        TelephonyHelper.getInstance();
        SubscriptionHelper.getInstance();

        new GlobalInitAsyncTask().execute();
    }

    public static Context getAppContext(){
        return sInstance.mContext;
    }

    private PhoneAccountHelper mPhoneAccountHelper = new PhoneAccountHelper();

    private BeautyGlobalReceiver mBeautyGlobalReceiver;

    public PhoneAccountHelper getPhoneAccountHelper() {
        return mPhoneAccountHelper;
    }

    private class GlobalInitAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // do ui pre-check before async task
        }

        @Override
        protected Void doInBackground(Void... voids) {
            registerBeautyGlobalReceiver();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // do some ui check after async task
        }
    }

    private void registerBeautyGlobalReceiver(){
        if (mBeautyGlobalReceiver == null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(TelecomManager.ACTION_DEFAULT_DIALER_CHANGED);
            filter.addAction(TelecomManager.ACTION_PHONE_ACCOUNT_REGISTERED);
            filter.addAction(TelecomManager.ACTION_PHONE_ACCOUNT_UNREGISTERED);
//            filter.addAction(TelephonyIntents.ACTION_SIM_STATE_CHANGED);
            mBeautyGlobalReceiver = new BeautyGlobalReceiver();
            mContext.registerReceiver(mBeautyGlobalReceiver, filter);
        }
    }

    private static class BeautyGlobalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TelecomManager.ACTION_DEFAULT_DIALER_CHANGED.equals(action)) {
//                sInstance.mDefDialerHelper.checkDefDialerPkg();
            } else if (TelecomManager.ACTION_PHONE_ACCOUNT_REGISTERED.equals(action) ||
                TelecomManager.ACTION_PHONE_ACCOUNT_UNREGISTERED.equals(action)) {
                // TODO
            } else if (TelephonyManager.ACTION_PHONE_STATE_CHANGED.equals(action)) {
                // TODO
            }
        }
    }
}
