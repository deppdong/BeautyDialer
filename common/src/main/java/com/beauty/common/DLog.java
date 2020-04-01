package com.beauty.common;

public class DLog {

    private final static String DIALER = "D-Dialer";

    private final static String CALL_LOG = "D-CallLog";

    private final static String CONNECTION_SERVICE = "D-MockConnectionService";

    private final static String INCALL = "D-InCall";

    private final static String SETTINGS = "D-Settings";

    public static void logDialer(String msg) {
        d(DIALER, msg);
    }

    public static void logCallLog(String msg) {
        d(CALL_LOG, msg);
    }

    public static void logConnectionService(String msg) {
        d(CONNECTION_SERVICE, msg);
    }

    public static void logInCall(String msg) {
        d(INCALL, msg);
    }

    public static void logSettings(String msg) {
        d(INCALL, msg);
    }

    public static void d(String tag, String msg) {
        android.util.Log.i(tag, msg);
    }

    public static void trace(String tag, Exception e) {
        StackTraceElement[] stes = e.getStackTrace();
        int count = 0;
        for (StackTraceElement ste : stes) {
            android.util.Log.i(tag, ste.toString());
            if (count++ > 4) {
                break;
            }
        }
    }

    public static void trace(Exception e) {
        StackTraceElement[] stes = e.getStackTrace();
        int count = 0;
        for (StackTraceElement ste : stes) {
            android.util.Log.i("DException", ste.toString());
            if (count++ > 4) {
                break;
            }
        }
    }
}
