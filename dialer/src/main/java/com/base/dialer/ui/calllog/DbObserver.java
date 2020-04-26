package com.base.dialer.ui.calllog;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.CallLog;
import android.provider.ContactsContract;

import com.zyq.easypermission.EasyPermission;

public class DbObserver {
    private ContentObserver mCallLogChangeObserver;
    private ContentObserver mContactsChangeObserver;

    public final int MSG_CALL_LOG_CHANGED = 1;
    public final int MSG_CONTACTS_CHANGED = 2;

    private Context mContext;
    private Handler mHandler;

    private DbObserver(Context context, Handler handler) {
        mContext = context;
        mHandler = handler;
    }

    private void registerCallLogObserver() {
        if (EasyPermission.build().hasPermission(mContext, Manifest.permission.READ_CALL_LOG)) {
            mCallLogChangeObserver = new ContentObserver(mHandler) {
                @Override
                public void onChange(boolean selfChange) {
                    super.onChange(selfChange);
                    mHandler.sendMessage(mHandler.obtainMessage(MSG_CALL_LOG_CHANGED));
                }
            };
            mContext.getContentResolver().registerContentObserver(CallLog.CONTENT_URI, true, mCallLogChangeObserver);
        }
    }

    private void registerContactsObserver() {
        if (EasyPermission.build().hasPermission(mContext, Manifest.permission.READ_CONTACTS)) {
            mContactsChangeObserver  = new ContentObserver(mHandler) {
                @Override
                public void onChange(boolean selfChange) {
                    super.onChange(selfChange);
                    mHandler.sendMessage(mHandler.obtainMessage(MSG_CONTACTS_CHANGED));
                }
            };
            mContext.getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, mContactsChangeObserver);
        }
    }

    public static class Builder {
        private Context context;
        private Handler handler;

        private boolean mEnableCallLogObserver;
        private boolean mEnableContactsObserver;
        public Builder(Context context, Handler handler) {
            this.context = context;
            this.handler = handler;
        }

        public Builder enableCallLogObserver() {
            mEnableCallLogObserver = true;
            return this;
        }

        public Builder enableContactsObserver() {
            mEnableContactsObserver = true;
            return this;
        }

        public DbObserver build() {
            DbObserver dbObserver = new DbObserver(context, handler);
            if (mEnableCallLogObserver) dbObserver.registerCallLogObserver();
            if (mEnableContactsObserver) dbObserver.registerContactsObserver();
            return dbObserver;
        }
    }

}
