package com.base.dialer.ui.calllog;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CallLogViewModel extends ViewModel {

    private MutableLiveData<Cursor> mCallLogData;

    public CallLogViewModel() {
        mCallLogData = new MutableLiveData<>();
    }

    public LiveData<Cursor> getCallLog() {
        return mCallLogData;
    }
}