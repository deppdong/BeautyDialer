package com.base.dialer.ui.calllog;

import android.content.Context;
import android.provider.CallLog;
import android.text.format.DateFormat;

public class CallLogQueryHelper {

    private final String[] CALL_LOG_ALL_PROJECT = new String[]{
            CallLog.Calls._ID,
            CallLog.Calls.DATE, // 日期
            CallLog.Calls.NUMBER,
            CallLog.Calls.DURATION, // 时长
            CallLog.Calls.CACHED_PHOTO_ID, // 联系人头像
            CallLog.Calls.CACHED_NAME,  // 联系人名字
            CallLog.Calls.GEOCODED_LOCATION, // 归属地
            CallLog.Calls.TYPE,  //来去电、未接、语音信箱等
            CallLog.Calls.PHONE_ACCOUNT_ID,  // 卡
            CallLog.Calls.IS_READ,  //已读
    };
    private static final String[] CALL_LOG_PROJECTION = new String[] {
            CallLog.Calls._ID,
            CallLog.Calls.DATE,
            CallLog.Calls.DURATION,
            CallLog.Calls.NUMBER,
            CallLog.Calls.TYPE,  // 来去电、未接、拒接
            CallLog.Calls.COUNTRY_ISO,        // 国家吗
            CallLog.Calls.GEOCODED_LOCATION,  //归属地
            CallLog.Calls.NUMBER_PRESENTATION,
            CallLog.Calls.PHONE_ACCOUNT_COMPONENT_NAME,
            CallLog.Calls.PHONE_ACCOUNT_ID,
            CallLog.Calls.IS_READ,   // 已读
            CallLog.Calls.FEATURES,  // 语音、视频通话、HD
            CallLog.Calls.DATA_USAGE,  //使用的数据流量
//            CallLog.Calls.TRANSCRIPTION  //语音邮件相关
    };

    public static String getLastOutgoingCallNumber(Context context) {
        return CallLog.Calls.getLastOutgoingCall(context);
    }

    public static boolean is24Hour(Context context) {
        return DateFormat.is24HourFormat(context);
    }

}
