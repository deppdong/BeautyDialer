package com.beauty.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;

import com.beauty.dialer.BeautyApplication;
import com.beauty.utils.DLog;

public class BeautyGlobal {

    private static BeautyGlobal sInstance = new BeautyGlobal();

    public static BeautyGlobal getInstance() {
        return sInstance;
    }

    private BeautyDefDialerHelper mDefDialerHelper = new BeautyDefDialerHelper();
    private BeautyPhoneAccountHelper mPhoneAccountHelper = new BeautyPhoneAccountHelper();

    private BeautyGlobalReceiver mBeautyGlobalReceiver;

    public void initAsync() {
        new GlobalInitAsyncTask().execute();
    }

    public BeautyDefDialerHelper getDefDialerHelper() {
        return mDefDialerHelper;
    }

    public BeautyPhoneAccountHelper getPhoneAccountHelper() {
        return mPhoneAccountHelper;
    }

    private class GlobalInitAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // do ui pre-check before async task
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDefDialerHelper.checkDefDialerPkg();

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
            BeautyApplication.getContext().registerReceiver(mBeautyGlobalReceiver, filter);
        }
    }

    private static class BeautyGlobalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            DLog.logDialer("BeautyGlobalReceiver " + action);
            if (TelecomManager.ACTION_DEFAULT_DIALER_CHANGED.equals(action)) {
                sInstance.mDefDialerHelper.checkDefDialerPkg();
            } else if (TelecomManager.ACTION_PHONE_ACCOUNT_REGISTERED.equals(action) ||
                TelecomManager.ACTION_PHONE_ACCOUNT_UNREGISTERED.equals(action)) {
                // TODO
            } else if (TelephonyManager.ACTION_PHONE_STATE_CHANGED.equals(action)) {
                // TODO
            }
        }
    }
}
