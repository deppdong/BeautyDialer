package com.base.common;

import android.app.Activity;
import android.app.role.RoleManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;

public class DefaultDialerHelper {

    public static void requestSelfAsDefDialer(Activity activity) {
        final String packageName = activity.getComponentName().getPackageName();
        changeDefaultDialer(activity, packageName);
    }

    public static void resetSystemDialerAsDefault(Activity activity) {
        final String systemDialer = TelecomHelper.getInstance().getSystemDialer();
        changeDefaultDialer(activity, systemDialer);
    }

    private static void changeDefaultDialer(Activity activity, String newDialer) {
        String curDefDialer = TelecomHelper.getInstance().getDefaultDialer();
        if (newDialer.equals(curDefDialer)) {
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            RoleManager rm = (RoleManager) activity.getSystemService(Context.ROLE_SERVICE);
            Intent intent = rm.createRequestRoleIntent(RoleManager.ROLE_DIALER);
            intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, newDialer);
            // 返回结果：RESULT_OK or RESULT_CANCELED
            activity.startActivityForResult(intent, 1);
        } else {
            Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER);
            intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, newDialer);
            try {
                activity.startActivity(intent);
            } catch (ActivityNotFoundException ane) {
                ane.printStackTrace();
            }
        }
    }

}
